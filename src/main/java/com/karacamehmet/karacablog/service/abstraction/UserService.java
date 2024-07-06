package com.karacamehmet.karacablog.service.abstraction;

import com.karacamehmet.karacablog.dto.request.RegisterRequest;
import com.karacamehmet.karacablog.dto.response.GetUserResponse;
import com.karacamehmet.karacablog.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User createUser(RegisterRequest request);

    User findUserByUsername(String author);

    GetUserResponse getUserByUserName(String userName);
}

