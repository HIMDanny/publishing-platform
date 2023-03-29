package com.publishing.article.repo;

import com.publishing.article.model.Bookmark;
import com.publishing.article.model.Like;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookmarkRepository extends JpaRepository<Bookmark, Integer> {
    Page<Bookmark> findAllByUserId(Integer userId, Pageable pageable);
}
