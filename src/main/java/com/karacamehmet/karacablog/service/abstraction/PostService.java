package com.karacamehmet.karacablog.service.abstraction;

import com.karacamehmet.karacablog.core.paging.PageInfo;
import com.karacamehmet.karacablog.dto.request.CreatePostRequest;
import com.karacamehmet.karacablog.dto.request.UpdatePostRequest;
import com.karacamehmet.karacablog.dto.response.*;
import com.karacamehmet.karacablog.model.Post;

public interface PostService {

    CreatePostResponse create(CreatePostRequest request);

    GetAllPostsListResponse getAll(PageInfo pageInfo);

    GetPostResponse getByUniqueNum(String uniqueNum);

    UpdatePostResponse updateByUniqueNum(String uniqueNum, UpdatePostRequest request);

    Void deleteByUniqueNum(String uniqueNum);

    Post findByUniqueNum(String uniqueNum);

    SearchPostListResponse searchByTitleOrContent(String keyword, PageInfo pageInfo);

    SearchPostListResponse searchByTag(String keyword, PageInfo pageInfo);
}
