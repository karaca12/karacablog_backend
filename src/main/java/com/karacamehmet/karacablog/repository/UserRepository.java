package com.karacamehmet.karacablog.repository;

import com.karacamehmet.karacablog.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    @Query("select distinct u from User u where u.isDeleted=false " +
            "and (lower(u.username) like lower(concat('%', :keyword, '%')))")
    Page<User> searchByKeyword(String keyword, Pageable pageable);
}