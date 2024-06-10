package com.karacamehmet.karacablog.service.abstraction;

import com.karacamehmet.karacablog.dto.request.LoginRequest;
import com.karacamehmet.karacablog.dto.request.RegisterRequest;
import com.karacamehmet.karacablog.dto.response.LoginResponse;
import com.karacamehmet.karacablog.dto.response.RegisterResponse;

public interface AuthService {
    RegisterResponse register(RegisterRequest request);

    LoginResponse login(LoginRequest request);
}
