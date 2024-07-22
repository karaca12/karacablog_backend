package com.karacamehmet.karacablog.service.implementation;

import com.karacamehmet.karacablog.core.paging.PageInfo;
import com.karacamehmet.karacablog.dto.request.ChangePasswordRequest;
import com.karacamehmet.karacablog.dto.request.RegisterRequest;
import com.karacamehmet.karacablog.dto.request.UpdateUserRequest;
import com.karacamehmet.karacablog.dto.response.GetUserResponse;
import com.karacamehmet.karacablog.dto.response.SearchUserListResponse;
import com.karacamehmet.karacablog.dto.response.UpdateUserResponse;
import com.karacamehmet.karacablog.model.User;
import com.karacamehmet.karacablog.repository.UserRepository;
import com.karacamehmet.karacablog.service.abstraction.RoleService;
import com.karacamehmet.karacablog.service.abstraction.UserService;
import com.karacamehmet.karacablog.service.mapper.UserMapper;
import com.karacamehmet.karacablog.service.rules.UserBusinessRules;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private final UserBusinessRules businessRules;
    private final RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return businessRules.getUserFromOptional(userRepository.findByUsername(username));
    }

    @Override
    public User createUser(RegisterRequest request) {
        businessRules.checkIfUserAlreadyExists(request.getUsername());
        User user = UserMapper.INSTANCE.getUserFromRegisterRequest(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRoles(roleService.getRoleUser());
        return userRepository.save(user);
    }

    @Override
    public User findUserByUsername(String username) {
        return businessRules.getUserFromOptional(userRepository.findByUsername(username));
    }

    @Override
    public GetUserResponse getByUserName(String username) {
        User user = findUserByUsername(username);
        return UserMapper.INSTANCE.getUserResponseFromUser(user);
    }

    @Override
    public UpdateUserResponse updateByUserName(String username, UpdateUserRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        businessRules.checkIfJWTUsernameMatchesRequestUsername(authentication.getName(), username);
        User user = findUserByUsername(username);
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setBirthDate(request.getBirthDate());
        return UserMapper.INSTANCE.getUpdateUserResponseFromUser(userRepository.save(user));
    }

    @Override
    public Void changePasswordByUserName(String username, ChangePasswordRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        businessRules.checkIfJWTUsernameMatchesRequestUsername(authentication.getName(), username);
        User user = findUserByUsername(username);
        businessRules.checkIfPasswordsMatch(user.getPassword(), request);
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
        return null;
    }

    @Override
    public SearchUserListResponse searchByKeyword(String keyword, PageInfo pageInfo) {
        Pageable pageable = PageRequest.of(pageInfo.getPage(), pageInfo.getSize());
        Page<User> usersPage = userRepository.searchByKeyword(keyword,pageable);
        SearchUserListResponse response = new SearchUserListResponse();
        response.setUsers(UserMapper.INSTANCE.getSearchUserResponsesFromUsers(usersPage.getContent()));
        response.setTotalPages(usersPage.getTotalPages());
        return response;
    }
}
