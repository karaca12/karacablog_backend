package com.karacamehmet.karacablog.service.implementation;

import com.karacamehmet.karacablog.dto.request.RegisterRequest;
import com.karacamehmet.karacablog.model.User;
import com.karacamehmet.karacablog.repository.UserRepository;
import com.karacamehmet.karacablog.service.abstraction.RoleService;
import com.karacamehmet.karacablog.service.abstraction.UserService;
import com.karacamehmet.karacablog.service.mapper.UserMapper;
import com.karacamehmet.karacablog.service.rules.UserBusinessRules;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserBusinessRules userBusinessRules;
    private final RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userBusinessRules.getUserFromOptional(userRepository.findByUsername(username));
    }

    @Override
    public User createUser(RegisterRequest request) {
        userBusinessRules.checkIfUserAlreadyExists(request.getUsername());
        User user = UserMapper.INSTANCE.getUserFromRegisterRequest(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRoles(roleService.getRoleUser());
        return userRepository.save(user);
    }

    @Override
    public User findUserByUsername(String username) {
        return userBusinessRules.getUserFromOptional(userRepository.findByUsername(username));
    }
}
