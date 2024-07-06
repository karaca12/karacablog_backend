package com.karacamehmet.karacablog.service.abstraction;

import com.karacamehmet.karacablog.core.paging.PageInfo;
import com.karacamehmet.karacablog.dto.request.CreatePostRequest;
import com.karacamehmet.karacablog.dto.request.UpdatePostRequest;
import com.karacamehmet.karacablog.dto.response.*;
import com.karacamehmet.karacablog.model.Post;

import java.util.List;

public interface PostService {

    CreatePostResponse createPost(CreatePostRequest request);

    GetAllPostsListResponse getAllPosts(PageInfo pageInfo);

    GetPostResponse getPostByUniqueNum(String uniqueNum);

    UpdatePostResponse updatePostByUniqueNum(String uniqueNum, UpdatePostRequest request);

    Void deletePostByUniqueNum(String uniqueNum);

    Post findByUniqueNum(String uniqueNum);

    List<SearchPostResponse> searchByTitleOrContent(String keyword, PageInfo pageInfo);
}
