package com.publishing.article.repo;

import com.publishing.article.model.Like;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Integer> {
    Page<Like> findAllByUserId(Integer userId, Pageable pageable);
}
