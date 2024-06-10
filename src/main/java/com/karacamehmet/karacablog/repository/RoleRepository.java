package com.karacamehmet.karacablog.repository;

import com.karacamehmet.karacablog.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Set<Role> findByName(String name);
}