package com.publishing.article.repo;

import com.publishing.article.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Integer> {

    List<Article> findAllByCategoryId(Integer categoryId);

    List<Article> findAllByAuthorId(Integer userId);
}
