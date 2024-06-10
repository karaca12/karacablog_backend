package com.karacamehmet.karacablog.service.abstraction;

import com.karacamehmet.karacablog.core.paging.PageInfo;
import com.karacamehmet.karacablog.dto.request.CreatePostRequest;
import com.karacamehmet.karacablog.dto.request.UpdatePostRequest;
import com.karacamehmet.karacablog.dto.response.CreatePostResponse;
import com.karacamehmet.karacablog.dto.response.GetAllPostsResponse;
import com.karacamehmet.karacablog.dto.response.GetPostResponse;
import com.karacamehmet.karacablog.dto.response.UpdatePostResponse;

import java.util.List;

public interface PostService {

    CreatePostResponse createPost(CreatePostRequest request);

    List<GetAllPostsResponse> getAllPosts(PageInfo pageInfo);

    GetPostResponse getPostByUniqueNum(String uniqueNum);

    UpdatePostResponse updatePostByUniqueNum(String uniqueNum, UpdatePostRequest request);

    Void deletePostByUniqueNum(String uniqueNum);
}
