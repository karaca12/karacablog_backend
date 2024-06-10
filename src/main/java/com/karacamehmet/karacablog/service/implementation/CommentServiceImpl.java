package com.karacamehmet.karacablog.service.implementation;

import com.karacamehmet.karacablog.core.paging.PageInfo;
import com.karacamehmet.karacablog.dto.request.CreateCommentRequest;
import com.karacamehmet.karacablog.dto.request.UpdateCommentRequest;
import com.karacamehmet.karacablog.dto.response.CreateCommentResponse;
import com.karacamehmet.karacablog.dto.response.GetAllCommentsOfPostResponse;
import com.karacamehmet.karacablog.dto.response.GetCommentResponse;
import com.karacamehmet.karacablog.dto.response.UpdateCommentResponse;
import com.karacamehmet.karacablog.model.Comment;
import com.karacamehmet.karacablog.repository.CommentRepository;
import com.karacamehmet.karacablog.service.abstraction.CommentService;
import com.karacamehmet.karacablog.service.abstraction.PostService;
import com.karacamehmet.karacablog.service.abstraction.UserService;
import com.karacamehmet.karacablog.service.event.PostDeletedEvent;
import com.karacamehmet.karacablog.service.mapper.CommentMapper;
import com.karacamehmet.karacablog.service.rules.CommentBusinessRules;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final CommentBusinessRules businessRules;
    private final UserService userService;
    private final PostService postService;

    @Override
    public CreateCommentResponse createCommentToPost(String postUniqueNum, CreateCommentRequest request) {
        Comment comment = CommentMapper.INSTANCE.getCommentFromCreateCommentRequest(request);
        comment.setUniqueNum(businessRules.generateCommentUniqueNum());
        comment.setUser(userService.findUserByUsername(request.getAuthor()));
        comment.setPost(postService.findByUniqueNum(postUniqueNum));
        return CommentMapper.INSTANCE.getCreateCommentResponseFromComment(commentRepository.save(comment));
    }

    @Override
    public List<GetAllCommentsOfPostResponse> getAllCommentsOfPostByPostUniqueNum(PageInfo pageInfo, String postUniqueNum) {
        Pageable pageable = PageRequest.of(pageInfo.getPage(), pageInfo.getSize());
        postService.findByUniqueNum(postUniqueNum);
        List<Comment> comments = commentRepository.findByPost_UniqueNumAndIsDeletedFalseOrderByUpdatedAtDesc(postUniqueNum, pageable);
        return CommentMapper.INSTANCE.getGetAllCommentsOfPostResponsesFromComments(comments);
    }

    @Override
    public GetCommentResponse getCommentByUniqueNum(String uniqueNum) {
        Comment comment = businessRules.getCommentFromOptional(commentRepository.findByUniqueNumAndIsDeletedFalse(uniqueNum));
        return CommentMapper.INSTANCE.getGetCommentResponseFromComment(comment);
    }

    //todo: for some reason updatedAt doesnt get updated at response
    @Override
    public UpdateCommentResponse updateCommentByUniqueNum(String uniqueNum, UpdateCommentRequest request) {
        Comment comment = businessRules.getCommentFromOptional(commentRepository.findByUniqueNumAndIsDeletedFalse(uniqueNum));
        comment.setContent(request.getContent());
        return CommentMapper.INSTANCE.getUpdateCommentResponseFromComment(commentRepository.save(comment));
    }

    @Override
    public Void deleteCommentByUniqueNum(String uniqueNum) {
        Comment comment = businessRules.getCommentFromOptional(commentRepository.findByUniqueNumAndIsDeletedFalse(uniqueNum));
        comment.setDeleted(true);
        comment.setDeletedAt(LocalDateTime.now());
        commentRepository.save(comment);
        return null;
    }

    @Override
    public void deleteAllCommentsOfPostWhileDeletingPost(List<Comment> comments) {
        for (Comment comment : comments) {
            comment.setDeleted(true);
            comment.setDeletedAt(LocalDateTime.now());
            commentRepository.save(comment);
        }
    }

    @EventListener
    public void handlePostDeletedEvent(PostDeletedEvent event) {
        deleteAllCommentsOfPostWhileDeletingPost(event.getComments());
    }
}
