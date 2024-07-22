package com.karacamehmet.karacablog.service.mapper;

import com.karacamehmet.karacablog.dto.request.CreateCommentRequest;
import com.karacamehmet.karacablog.dto.response.CreateCommentResponse;
import com.karacamehmet.karacablog.dto.response.GetAllCommentsOfPostResponse;
import com.karacamehmet.karacablog.dto.response.GetCommentResponse;
import com.karacamehmet.karacablog.dto.response.UpdateCommentResponse;
import com.karacamehmet.karacablog.model.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    Comment getCommentFromCreateCommentRequest(CreateCommentRequest request);

    @Mapping(target = "author", source = "user.username")
    CreateCommentResponse getCreateCommentResponseFromComment(Comment comment);

    //used by MapStruct
    @Mapping(target = "author", source = "user.username")
    GetAllCommentsOfPostResponse getGetAllCommentsOfPostResponseFromComment(Comment comment);

    List<GetAllCommentsOfPostResponse> getGetAllCommentsOfPostResponsesFromComments(List<Comment> comments);

    @Mapping(target = "author", source = "user.username")
    GetCommentResponse getGetCommentResponseFromComment(Comment comment);

    @Mapping(target = "author", source = "user.username")
    UpdateCommentResponse getUpdateCommentResponseFromComment(Comment comment);
}
