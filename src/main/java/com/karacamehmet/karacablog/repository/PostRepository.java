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

    List<Post> findByIsDeletedFalseOrderByCreatedAtDesc(Pageable pageable);

    Optional<Post> findByUniqueNumAndIsDeletedFalse(String uniqueNum);

    @Query("select distinct p from Post p left join p.tags t where p.isDeleted=false " +
            "and (lower(p.title) like lower(concat('%', :keyword, '%')) " +
            "or lower(p.content) like lower(concat('%', :keyword, '%')) " +
            "or lower(t.name) like lower(concat('%', :keyword, '%')))")
    List<Post> searchByTitleContentOrTag(@Param("keyword") String keyword, Pageable pageable);




    long countByIsDeletedFalse();
}