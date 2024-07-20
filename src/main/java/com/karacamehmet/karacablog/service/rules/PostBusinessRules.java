package com.karacamehmet.karacablog.service.rules;

import com.karacamehmet.karacablog.core.exception.type.BusinessException;
import com.karacamehmet.karacablog.core.message.constant.Messages;
import com.karacamehmet.karacablog.core.message.service.MessageService;
import com.karacamehmet.karacablog.model.Post;
import com.karacamehmet.karacablog.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class PostBusinessRules {
    private final MessageService messageService;
    private final PostRepository postRepository;
    private final Random random = new Random();

    public String generatePostUniqueNum() {
        String uniqueNum = generateUniquePostUniqueNum();
        if (!isUniqueNumUnique(uniqueNum)) {
            return uniqueNum;
        } else {
            return generatePostUniqueNum();
        }
    }

    private String generateUniquePostUniqueNum() {
        StringBuilder uniqueNumBuilder = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            uniqueNumBuilder.append(random.nextInt(10));
        }
        return uniqueNumBuilder.toString();
    }

    private boolean isUniqueNumUnique(String uniqueNum) {
        return postRepository.existsByUniqueNum(uniqueNum);
    }

    public Post getPostFromOptional(Optional<Post> optionalPost) {
        return optionalPost.orElseThrow(() -> new BusinessException(messageService.getMessage(Messages.BusinessErrors.NO_POST_FOUND)));

    }

    public long checkIfPostCountIsMultipleOfPageSizeAndReturnPageCount(int pageSize) {
        long postCount = postRepository.countByIsDeletedFalse();
        if (postCount % pageSize == 0) {
            return postCount / pageSize;
        } else {
            return postCount / pageSize + 1;
        }
    }

    public void checkIfJWTUsernameMatchesRequestAuthor(String username, String author) {
        if (!username.equals(author)) {
            throw new BusinessException(messageService.getMessage(Messages.BusinessErrors.USERS_DONT_MATCH));
        }
    }
}
