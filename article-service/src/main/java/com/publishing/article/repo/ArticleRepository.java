package com.publishing.article.repo;

import com.publishing.article.model.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Arrays;
import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Integer> {

    List<Article> findAllByCategoryId(Integer categoryId);

    List<Article> findAllByAuthorId(Integer userId);

    @Query("SELECT a FROM Article a WHERE " +
            "a.title LIKE CONCAT('%',:query, '%') " +
            "OR a.content LIKE CONCAT('%', :query, '%')")
    List<Article> searchArticles(@Param("query") String query, Sort sort);

    @Query(value = "SELECT * FROM Article a WHERE " +
                "a.title LIKE CONCAT('%',:query, '%') " +
                "OR a.content LIKE CONCAT('%', :query, '%')",
            countQuery = "SELECT count(*) FROM Article a WHERE " +
                    "a.title LIKE CONCAT('%',:query, '%') " +
                    "OR a.content LIKE CONCAT('%', :query, '%')",
            nativeQuery = true)
    Page<Article> searchArticlesWithPagination(@Param("query") String query, Pageable pageable);

}
