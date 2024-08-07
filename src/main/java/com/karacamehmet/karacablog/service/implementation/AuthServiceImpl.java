package com.karacamehmet.karacablog.service.implementation;

import com.karacamehmet.karacablog.core.jwt.service.JwtService;
import com.karacamehmet.karacablog.dto.request.LoginRequest;
import com.karacamehmet.karacablog.dto.request.RegisterRequest;
import com.karacamehmet.karacablog.model.User;
import com.karacamehmet.karacablog.service.abstraction.AuthService;
import com.karacamehmet.karacablog.service.abstraction.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtService jwtService;

    @Override
    public String register(RegisterRequest request) {
        User user = userService.createUser(request);
        return jwtService.generateToken(user.getUsername(), user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList());
    }

    @Override
    public String login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user = userService.loadUserByUsername(request.getUsername());
        return jwtService.generateToken(user.getUsername(), user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList());
    }
}
