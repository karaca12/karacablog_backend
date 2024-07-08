package com.karacamehmet.karacablog.repository;

import com.karacamehmet.karacablog.model.Comment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    boolean existsByUniqueNum(String uniqueNum);

    List<Comment> findByPost_UniqueNumAndIsDeletedFalseOrderByCreatedAtDesc(String uniqueNum, Pageable pageable);

    Optional<Comment> findByUniqueNumAndIsDeletedFalse(String uniqueNum);

    long countByIsDeletedFalseAndPost_UniqueNum(String uniqueNum);
}