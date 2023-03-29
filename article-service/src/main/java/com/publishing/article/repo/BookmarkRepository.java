package com.publishing.article.repo;

import com.publishing.article.model.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookmarkRepository extends JpaRepository<Bookmark, Integer> {
}
