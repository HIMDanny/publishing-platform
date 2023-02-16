package com.publishing.article;

import com.publishing.clients.article.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Integer> {

    List<Article> findAllByCategoryId(Integer categoryId);

}
