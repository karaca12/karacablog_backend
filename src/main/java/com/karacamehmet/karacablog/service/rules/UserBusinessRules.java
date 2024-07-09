package com.karacamehmet.karacablog.service.rules;

import com.karacamehmet.karacablog.core.exception.type.BusinessException;
import com.karacamehmet.karacablog.core.message.constant.Messages;
import com.karacamehmet.karacablog.core.message.service.MessageService;
import com.karacamehmet.karacablog.dto.request.ChangePasswordRequest;
import com.karacamehmet.karacablog.model.User;
import com.karacamehmet.karacablog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserBusinessRules {
    private final MessageService messageService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public User getUserFromOptional(Optional<User> optionalUser) {
        return optionalUser.orElseThrow(() -> new BusinessException(messageService.getMessage(Messages.BusinessErrors.NO_USER_FOUND)));
    }

    public void checkIfUserAlreadyExists(String username) {
        if (userRepository.existsByUsername(username)) {
            throw new BusinessException(messageService.getMessage(Messages.BusinessErrors.USER_ALREADY_EXISTS));
        }
    }

    public void checkIfJWTUsernameMatchesRequestUsername(String jwtUsername, String userName) {
        if (!jwtUsername.equals(userName)) {
            throw new BusinessException(messageService.getMessage(Messages.BusinessErrors.USERS_DONT_MATCH));
        }
    }

    public void checkIfPasswordsMatch(String userPassword, ChangePasswordRequest request) {
        if (!request.getNewPassword().equals(request.getConfirmNewPassword())){
            throw new BusinessException(messageService.getMessage(Messages.BusinessErrors.PASSWORDS_DONT_MATCH));
        }
        if (!passwordEncoder.matches(request.getCurrentPassword(), userPassword)){
            throw new BusinessException(messageService.getMessage(Messages.BusinessErrors.WRONG_USERNAME_OR_PASSWORD));
        }
    }
}
