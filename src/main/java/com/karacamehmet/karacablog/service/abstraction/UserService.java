package com.karacamehmet.karacablog.service.abstraction;

import com.karacamehmet.karacablog.dto.request.ChangePasswordRequest;
import com.karacamehmet.karacablog.dto.request.RegisterRequest;
import com.karacamehmet.karacablog.dto.request.UpdateUserRequest;
import com.karacamehmet.karacablog.dto.response.GetUserResponse;
import com.karacamehmet.karacablog.dto.response.UpdateUserResponse;
import com.karacamehmet.karacablog.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User createUser(RegisterRequest request);

    User findUserByUsername(String author);

    GetUserResponse getUserByUserName(String username);

    UpdateUserResponse updateUserByUserName(String username, UpdateUserRequest request);

    Void changePasswordByUserName(String username, ChangePasswordRequest request);
}

