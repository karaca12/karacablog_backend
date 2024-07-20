package com.karacamehmet.karacablog.service.abstraction;

import com.karacamehmet.karacablog.core.paging.PageInfo;
import com.karacamehmet.karacablog.dto.request.CreateCommentRequest;
import com.karacamehmet.karacablog.dto.request.UpdateCommentRequest;
import com.karacamehmet.karacablog.dto.response.CreateCommentResponse;
import com.karacamehmet.karacablog.dto.response.GetAllCommentsOfPostListResponse;
import com.karacamehmet.karacablog.dto.response.GetCommentResponse;
import com.karacamehmet.karacablog.dto.response.UpdateCommentResponse;
import com.karacamehmet.karacablog.model.Comment;

import java.util.List;

public interface CommentService {
    CreateCommentResponse createCommentToPost(String postUniqueNum, CreateCommentRequest request);

    GetAllCommentsOfPostListResponse getAllCommentsOfPostByPostUniqueNum(PageInfo pageInfo, String postUniqueNum);

    GetCommentResponse getCommentByUniqueNum(String uniqueNum);

    UpdateCommentResponse updateCommentByUniqueNum(String uniqueNum, UpdateCommentRequest request);

    Void deleteCommentByUniqueNum(String uniqueNum);

    void deleteAllCommentsOfPostWhileDeletingPost(List<Comment> comments);
}
