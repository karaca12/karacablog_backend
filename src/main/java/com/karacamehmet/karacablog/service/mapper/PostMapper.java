package com.karacamehmet.karacablog.service.mapper;

import com.karacamehmet.karacablog.dto.request.CreatePostRequest;
import com.karacamehmet.karacablog.dto.response.*;
import com.karacamehmet.karacablog.model.Post;
import com.karacamehmet.karacablog.model.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface PostMapper {
    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    @Mapping(target = "tags", ignore = true)
    Post getPostFromCreatePostRequest(CreatePostRequest createPostRequest);

    @Mapping(target = "author", source = "user.username")
    @Mapping(target = "tags", ignore = true)
    CreatePostResponse getCreatePostResponseFromPost(Post post);

    @Mapping(target = "author", source = "user.username")
    GetAllPostsResponse getGetAllPostsResponseFromPost(Post post);

    List<GetAllPostsResponse> getGetAllPostsResponsesFromPosts(List<Post> posts);

    default List<String> mapTagsToStringList(List<Tag> tags) {
        if (tags == null) {
            return null;
        }
        return tags.stream()
                .map(Tag::getName)
                .collect(Collectors.toList());
    }

    @Mapping(target = "author", source = "user.username")
    GetPostResponse getGetPostResponseFromPost(Post post);

    @Mapping(target = "author", source = "user.username")
    UpdatePostResponse getUpdatePostResponseFromPost(Post post);

    @Mapping(target = "author", source = "user.username")
    SearchPostResponse getSearchPostResponseFromPost(Post post);

    List<SearchPostResponse> getSearchPostResponsesFromPosts(List<Post> posts);
}
