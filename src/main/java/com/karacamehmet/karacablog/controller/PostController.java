package com.karacamehmet.karacablog.controller;

import com.karacamehmet.karacablog.core.paging.PageInfo;
import com.karacamehmet.karacablog.dto.request.CreatePostRequest;
import com.karacamehmet.karacablog.dto.request.UpdatePostRequest;
import com.karacamehmet.karacablog.dto.response.CreatePostResponse;
import com.karacamehmet.karacablog.dto.response.GetAllPostsResponse;
import com.karacamehmet.karacablog.dto.response.GetPostResponse;
import com.karacamehmet.karacablog.dto.response.UpdatePostResponse;
import com.karacamehmet.karacablog.service.abstraction.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping
    public ResponseEntity<CreatePostResponse> createPost(@RequestBody @Valid CreatePostRequest request) {
        return new ResponseEntity<>(postService.createPost(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<GetAllPostsResponse>> getAllPosts(@RequestParam int page, @RequestParam int size) {
        PageInfo pageInfo = new PageInfo(page, size);
        return new ResponseEntity<>(postService.getAllPosts(pageInfo), HttpStatus.OK);
    }

    @GetMapping("/{uniqueNum}")
    public ResponseEntity<GetPostResponse> getPostByUniqueNum(@PathVariable String uniqueNum) {
        return new ResponseEntity<>(postService.getPostByUniqueNum(uniqueNum), HttpStatus.OK);
    }

    @PutMapping("/{uniqueNum}")
    public ResponseEntity<UpdatePostResponse> updatePostByUniqueNum(@PathVariable String uniqueNum,
                                                                    @RequestBody @Valid UpdatePostRequest request) {
        return new ResponseEntity<>(postService.updatePostByUniqueNum(uniqueNum, request), HttpStatus.OK);
    }

    @DeleteMapping("/{uniqueNum}")
    public ResponseEntity<Void> deletePostByUniqueNum(@PathVariable String uniqueNum) {
        return new ResponseEntity<>(postService.deletePostByUniqueNum(uniqueNum), HttpStatus.NO_CONTENT);
    }
}
