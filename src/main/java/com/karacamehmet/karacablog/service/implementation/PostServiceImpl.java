package com.karacamehmet.karacablog.service.implementation;

import com.karacamehmet.karacablog.core.paging.PageInfo;
import com.karacamehmet.karacablog.dto.request.CreatePostRequest;
import com.karacamehmet.karacablog.dto.request.UpdatePostRequest;
import com.karacamehmet.karacablog.dto.response.CreatePostResponse;
import com.karacamehmet.karacablog.dto.response.GetAllPostsResponse;
import com.karacamehmet.karacablog.dto.response.GetPostResponse;
import com.karacamehmet.karacablog.dto.response.UpdatePostResponse;
import com.karacamehmet.karacablog.model.Post;
import com.karacamehmet.karacablog.repository.PostRepository;
import com.karacamehmet.karacablog.service.abstraction.PostService;
import com.karacamehmet.karacablog.service.abstraction.TagService;
import com.karacamehmet.karacablog.service.abstraction.UserService;
import com.karacamehmet.karacablog.service.mapper.PostMapper;
import com.karacamehmet.karacablog.service.rules.PostBusinessRules;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final UserService userService;
    private final TagService tagService;
    private final PostBusinessRules businessRules;


    @Override
    public CreatePostResponse createPost(CreatePostRequest request) {
        Post post = PostMapper.INSTANCE.getPostFromCreatePostRequest(request);
        post.setUniqueNum(businessRules.generatePostUniqueNum());
        post.setUser(userService.findUserByUsername(request.getAuthor()));
        post.setTags(tagService.findOrCreateTagsByNames(request.getTags()));
        CreatePostResponse response = PostMapper.INSTANCE.getCreatePostResponseFromPost(postRepository.save(post));
        response.setTags(request.getTags());
        return response;
    }

    @Override
    public List<GetAllPostsResponse> getAllPosts(PageInfo pageInfo) {
        Pageable pageable = PageRequest.of(pageInfo.getPage(), pageInfo.getSize());
        List<Post> posts = postRepository.findByIsDeletedFalse(pageable);
        return PostMapper.INSTANCE.getGetAllPostsResponsesFromPosts(posts);
    }

    @Override
    public GetPostResponse getPostByUniqueNum(String uniqueNum) {
        Post post = businessRules.getPostFromOptional(postRepository.findByUniqueNumAndIsDeletedFalse(uniqueNum));
        return PostMapper.INSTANCE.getGetPostResponseFromPost(post);
    }

    @Override
    public UpdatePostResponse updatePostByUniqueNum(String uniqueNum, UpdatePostRequest request) {
        Post post = businessRules.getPostFromOptional(postRepository.findByUniqueNumAndIsDeletedFalse(uniqueNum));
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setTags(tagService.findOrCreateTagsByNames(request.getTags()));
        return PostMapper.INSTANCE.getUpdatePostResponseFromPost(postRepository.save(post));
    }

    //todo: this should delete all the comments on the post
    @Override
    public Void deletePostByUniqueNum(String uniqueNum) {
        Post post = businessRules.getPostFromOptional(postRepository.findByUniqueNumAndIsDeletedFalse(uniqueNum));
        post.setDeleted(true);
        post.setDeletedAt(LocalDateTime.now());
        postRepository.save(post);
        return null;
    }
}
