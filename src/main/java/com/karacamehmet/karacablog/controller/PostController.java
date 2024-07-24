package com.karacamehmet.karacablog.controller;

import com.karacamehmet.karacablog.core.paging.PageInfo;
import com.karacamehmet.karacablog.dto.request.CreatePostRequest;
import com.karacamehmet.karacablog.dto.request.UpdatePostRequest;
import com.karacamehmet.karacablog.dto.response.*;
import com.karacamehmet.karacablog.service.abstraction.PostService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping
    public ResponseEntity<CreatePostResponse> create(@RequestBody @Valid CreatePostRequest request) {
        return new ResponseEntity<>(postService.create(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<GetAllPostsListResponse> getAll(@RequestParam int page, @RequestParam int size) {
        PageInfo pageInfo = new PageInfo(page, size);
        return new ResponseEntity<>(postService.getAll(pageInfo), HttpStatus.OK);
    }

    @GetMapping("{uniqueNum}")
    public ResponseEntity<GetPostResponse> getByUniqueNum(@PathVariable String uniqueNum) {
        return new ResponseEntity<>(postService.getByUniqueNum(uniqueNum), HttpStatus.OK);
    }

    @PutMapping("{uniqueNum}")
    public ResponseEntity<UpdatePostResponse> updateByUniqueNum(@PathVariable String uniqueNum,
                                                                    @RequestBody @Valid UpdatePostRequest request) {
        return new ResponseEntity<>(postService.updateByUniqueNum(uniqueNum, request), HttpStatus.OK);
    }

    @DeleteMapping("{uniqueNum}")
    public ResponseEntity<Void> deleteByUniqueNum(@PathVariable String uniqueNum) {
        return new ResponseEntity<>(postService.deleteByUniqueNum(uniqueNum), HttpStatus.NO_CONTENT);
    }

    @PostMapping("search/{keyword}")
    public ResponseEntity<SearchPostListResponse> searchByTitleOrContent(@PathVariable String keyword,
                                                                         @RequestParam int page, @RequestParam int size) {
        PageInfo pageInfo = new PageInfo(page, size);
        return new ResponseEntity<>(postService.searchByTitleOrContent(keyword, pageInfo), HttpStatus.OK);
    }

    @PostMapping("search/tag/{keyword}")
    public ResponseEntity<SearchPostListResponse> searchByTag(@PathVariable String keyword,
                                                                            @RequestParam int page, @RequestParam int size) {
        PageInfo pageInfo = new PageInfo(page, size);
        return new ResponseEntity<>(postService.searchByTag(keyword, pageInfo), HttpStatus.OK);
    }

    @GetMapping("user/{username}")
    public ResponseEntity<GetPostByUsernameListResponse> getByUsername(@PathVariable String username,
                                                                        @RequestParam int page, @RequestParam int size) {
        PageInfo pageInfo = new PageInfo(page, size);
        return new ResponseEntity<>(postService.getByUsername(username,pageInfo), HttpStatus.OK);
    }

}
