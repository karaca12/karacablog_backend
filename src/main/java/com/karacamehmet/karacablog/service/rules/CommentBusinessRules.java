package com.karacamehmet.karacablog.service.rules;

import com.karacamehmet.karacablog.core.exception.type.BusinessException;
import com.karacamehmet.karacablog.core.message.constant.Messages;
import com.karacamehmet.karacablog.core.message.service.MessageService;
import com.karacamehmet.karacablog.model.Comment;
import com.karacamehmet.karacablog.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class CommentBusinessRules {
    private final MessageService messageService;
    private final CommentRepository commentRepository;

    private final Random random = new Random();

    public String generateCommentUniqueNum() {
        String uniqueNum = generateUniqueCommentUniqueNum();
        if (!isUniqueNumUnique(uniqueNum)) {
            return uniqueNum;
        } else {
            return generateCommentUniqueNum();
        }
    }

    private String generateUniqueCommentUniqueNum() {
        StringBuilder uniqueNumBuilder = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            uniqueNumBuilder.append(random.nextInt(10));
        }
        return uniqueNumBuilder.toString();
    }

    private boolean isUniqueNumUnique(String uniqueNum) {
        return commentRepository.existsByUniqueNum(uniqueNum);
    }

    public Comment getCommentFromOptional(Optional<Comment> optionalComment) {
        return optionalComment.orElseThrow(() -> new BusinessException(messageService.getMessage(Messages.BusinessErrors.NO_COMMENT_FOUND)));

    }

    public long checkIfCommentsCountIsMultipleOfPageSizeAndReturnPageCount(int pageSize,String postUniqueNum) {
        long commentCount = commentRepository.countByIsDeletedFalseAndPost_UniqueNum(postUniqueNum);
        if (commentCount%pageSize == 0) {
            return commentCount/pageSize;
        }else {
            return commentCount/pageSize+1;
        }

    }

    public void checkIfJWTUsernameMatchesRequestAuthor(String username, String author) {
        if (!username.equals(author)) {
            throw new BusinessException(messageService.getMessage(Messages.BusinessErrors.USERS_DONT_MATCH));
        }
    }
}
