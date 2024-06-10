package com.karacamehmet.karacablog.service.implementation;

import com.karacamehmet.karacablog.model.Role;
import com.karacamehmet.karacablog.repository.RoleRepository;
import com.karacamehmet.karacablog.service.abstraction.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    @Value("${roleUser}")
    private String roleUser;

    @Override
    public Set<Role> getRoleUser() {
        return roleRepository.findByName(roleUser);
    }
}
