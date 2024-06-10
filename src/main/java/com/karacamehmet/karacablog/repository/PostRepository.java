package com.karacamehmet.karacablog.repository;

import com.karacamehmet.karacablog.model.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Integer> {
    boolean existsByUniqueNum(String uniqueNum);

    List<Post> findByIsDeletedFalse(Pageable pageable);

    Optional<Post> findByUniqueNumAndIsDeletedFalse(String uniqueNum);
}