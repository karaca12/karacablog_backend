package com.karacamehmet.karacablog.repository;

import com.karacamehmet.karacablog.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
}