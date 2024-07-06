package com.karacamehmet.karacablog.service.abstraction;

import com.karacamehmet.karacablog.dto.request.LoginRequest;
import com.karacamehmet.karacablog.dto.request.RegisterRequest;

public interface AuthService {
    String register(RegisterRequest request);

    String login(LoginRequest request);
}
