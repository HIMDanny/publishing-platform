package com.publishing.article.repo;

import com.publishing.article.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepo extends JpaRepository<Tag, Integer> {
}
