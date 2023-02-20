package com.publishing.article.repo;

import com.publishing.article.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Integer> {

    List<Article> findAllByCategoryId(Integer categoryId);

    List<Article> findAllByAuthorId(Integer userId);

    @Query("SELECT a FROM Article a WHERE " +
            "a.title LIKE CONCAT('%',:query, '%') " +
            "OR a.content LIKE CONCAT('%', :query, '%')")
    List<Article> searchArticles(String query);

    @Query("SELECT a FROM Article a WHERE " +
            "a.title LIKE CONCAT('%',:query, '%') " +
            "OR a.content LIKE CONCAT('%', :query, '%') " +
            "LIMIT :pageSize OFFSET (:offset*:pageSize)")
    List<Article> searchArticlesWithPagination(String query, Integer offset, Integer pageSize);
}
