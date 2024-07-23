package com.karacamehmet.karacablog.service.implementation;

import com.karacamehmet.karacablog.core.paging.PageInfo;
import com.karacamehmet.karacablog.dto.request.CreatePostRequest;
import com.karacamehmet.karacablog.dto.request.UpdatePostRequest;
import com.karacamehmet.karacablog.dto.response.*;
import com.karacamehmet.karacablog.model.Post;
import com.karacamehmet.karacablog.repository.PostRepository;
import com.karacamehmet.karacablog.service.abstraction.PostService;
import com.karacamehmet.karacablog.service.abstraction.TagService;
import com.karacamehmet.karacablog.service.abstraction.UserService;
import com.karacamehmet.karacablog.service.event.PostDeletedEvent;
import com.karacamehmet.karacablog.service.mapper.PostMapper;
import com.karacamehmet.karacablog.service.rules.PostBusinessRules;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
@RequiredArgsConstructor
@Transactional
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final UserService userService;
    private final TagService tagService;
    private final PostBusinessRules businessRules;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public CreatePostResponse create(CreatePostRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        businessRules.checkIfJWTUsernameMatchesRequestAuthor(authentication.getName(), request.getAuthor());
        Post post = PostMapper.INSTANCE.getPostFromCreatePostRequest(request);
        post.setUniqueNum(businessRules.generatePostUniqueNum());
        post.setUser(userService.findUserByUsername(request.getAuthor()));
        post.setTags(tagService.findOrCreateTagsByNames(request.getTags()));
        CreatePostResponse response = PostMapper.INSTANCE.getCreatePostResponseFromPost(postRepository.save(post));
        response.setTags(request.getTags());
        return response;
    }

    @Override
    public GetAllPostsListResponse getAll(PageInfo pageInfo) {
        Pageable pageable = PageRequest.of(pageInfo.getPage(), pageInfo.getSize());
        Page<Post> postsPage = postRepository.findByIsDeletedFalseOrderByCreatedAtDesc(pageable);
        GetAllPostsListResponse response = new GetAllPostsListResponse();
        response.setPosts(PostMapper.INSTANCE.getGetAllPostsResponsesFromPosts(postsPage.getContent()));
        response.setTotalPages(postsPage.getTotalPages());
        return response;
    }

    @Override
    public GetPostResponse getByUniqueNum(String uniqueNum) {
        Post post = businessRules.getPostFromOptional(postRepository.findByUniqueNumAndIsDeletedFalse(uniqueNum));
        return PostMapper.INSTANCE.getGetPostResponseFromPost(post);
    }

    @Override
    public UpdatePostResponse updateByUniqueNum(String uniqueNum, UpdatePostRequest request) {
        Post post = businessRules.getPostFromOptional(postRepository.findByUniqueNumAndIsDeletedFalse(uniqueNum));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        businessRules.checkIfJWTUsernameMatchesRequestAuthor(authentication.getName(), post.getUser().getUsername());
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setTags(tagService.findOrCreateTagsByNames(request.getTags()));
        return PostMapper.INSTANCE.getUpdatePostResponseFromPost(postRepository.save(post));
    }

    @Override
    public Void deleteByUniqueNum(String uniqueNum) {
        Post post = businessRules.getPostFromOptional(postRepository.findByUniqueNumAndIsDeletedFalse(uniqueNum));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        businessRules.checkIfJWTUsernameMatchesRequestAuthor(authentication.getName(), post.getUser().getUsername());
        post.setDeleted(true);
        post.setDeletedAt(LocalDateTime.now(ZoneOffset.UTC));
        eventPublisher.publishEvent(new PostDeletedEvent(this, post.getComments()));
        postRepository.save(post);
        return null;
    }

    @Override
    public Post findByUniqueNum(String uniqueNum) {
        return businessRules.getPostFromOptional(postRepository.findByUniqueNumAndIsDeletedFalse(uniqueNum));
    }

    @Override
    public SearchPostListResponse searchByTitleOrContent(String keyword, PageInfo pageInfo) {
        Pageable pageable = PageRequest.of(pageInfo.getPage(), pageInfo.getSize());
        Page<Post> postsPage = postRepository.searchByTitleOrContent(keyword, pageable);
        SearchPostListResponse response = new SearchPostListResponse();
        response.setPosts(PostMapper.INSTANCE.getSearchPostResponsesFromPosts(postsPage.getContent()));
        response.setTotalPages(postsPage.getTotalPages());
        return response;
    }

    @Override
    public SearchPostListResponse searchByTag(String keyword, PageInfo pageInfo) {
        Pageable pageable = PageRequest.of(pageInfo.getPage(), pageInfo.getSize());
        Page<Post> postsPage = postRepository.searchByTagName(keyword, pageable);
        SearchPostListResponse response = new SearchPostListResponse();
        response.setPosts(PostMapper.INSTANCE.getSearchPostResponsesFromPosts(postsPage.getContent()));
        response.setTotalPages(postsPage.getTotalPages());
        return response;
    }

    @Override
    public GetPostByUsernameListResponse getByUsername(String username, PageInfo pageInfo) {
        Pageable pageable = PageRequest.of(pageInfo.getPage(), pageInfo.getSize());
        Page<Post> postsPage = postRepository.findByIsDeletedFalseAndUser_UsernameOrderByCreatedAtDesc(username,pageable);
        GetPostByUsernameListResponse response = new GetPostByUsernameListResponse();
        response.setPosts(PostMapper.INSTANCE.getGetPostByUsernameResponsesFromPost(postsPage.getContent()));
        response.setTotalPages(postsPage.getTotalPages());
        return response;
    }
}
