package com.karacamehmet.karacablog.controller;

import com.karacamehmet.karacablog.core.paging.PageInfo;
import com.karacamehmet.karacablog.dto.request.CreateCommentRequest;
import com.karacamehmet.karacablog.dto.request.UpdateCommentRequest;
import com.karacamehmet.karacablog.dto.response.CreateCommentResponse;
import com.karacamehmet.karacablog.dto.response.GetAllCommentsOfPostListResponse;
import com.karacamehmet.karacablog.dto.response.GetCommentResponse;
import com.karacamehmet.karacablog.dto.response.UpdateCommentResponse;
import com.karacamehmet.karacablog.service.abstraction.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/post/{postUniqueNum}")
    public ResponseEntity<CreateCommentResponse> createCommentToPost(@PathVariable String postUniqueNum,
                                                                     @RequestBody CreateCommentRequest request) {
        return new ResponseEntity<>(commentService.createCommentToPost(postUniqueNum, request), HttpStatus.CREATED);
    }

    @GetMapping("/post/{postUniqueNum}")
    public ResponseEntity<GetAllCommentsOfPostListResponse> getAllByPostUniqueNum(
            @RequestParam int page, @RequestParam int size, @PathVariable String postUniqueNum) {
        PageInfo pageInfo = new PageInfo(page, size);
        return new ResponseEntity<>(commentService.getAllByPostUniqueNum(pageInfo, postUniqueNum), HttpStatus.OK);
    }

    @GetMapping("/{uniqueNum}")
    public ResponseEntity<GetCommentResponse> getByUniqueNum(@PathVariable String uniqueNum) {
        return new ResponseEntity<>(commentService.getByUniqueNum(uniqueNum), HttpStatus.OK);
    }

    @PutMapping("/{uniqueNum}")
    public ResponseEntity<UpdateCommentResponse> updateByUniqueNum(@PathVariable String uniqueNum,
                                                                          @RequestBody @Valid UpdateCommentRequest request) {
        return new ResponseEntity<>(commentService.updateByUniqueNum(uniqueNum, request), HttpStatus.OK);
    }

    @DeleteMapping("/{uniqueNum}")
    public ResponseEntity<Void> deleteByUniqueNum(@PathVariable String uniqueNum) {
        return new ResponseEntity<>(commentService.deleteByUniqueNum(uniqueNum), HttpStatus.NO_CONTENT);
    }
}
