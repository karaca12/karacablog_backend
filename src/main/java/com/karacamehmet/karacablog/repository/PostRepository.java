package com.karacamehmet.karacablog.repository;

import com.karacamehmet.karacablog.model.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Integer> {
    boolean existsByUniqueNum(String uniqueNum);

    List<Post> findByIsDeletedFalseOrderByUpdatedAtDesc(Pageable pageable);

    Optional<Post> findByUniqueNumAndIsDeletedFalse(String uniqueNum);

    @Query("select p from Post p where p.title like %:keyword% or p.content like %:keyword%")
    List<Post> searchByTitleOrContent(@Param("keyword") String keyword, Pageable pageable);


    long countByIsDeletedFalse();
}