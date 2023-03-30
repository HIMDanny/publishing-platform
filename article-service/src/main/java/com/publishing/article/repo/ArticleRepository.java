package com.publishing.article.repo;

import com.publishing.article.model.Article;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Arrays;
import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Integer> {

    Page<Article> findAllByCategoryId(Integer categoryId, Pageable pageable);

    Page<Article> findAllByAuthorId(Integer userId, Pageable pageable);

    @Modifying
    @Transactional
    @Query(value = "UPDATE article SET number_of_views=number_of_views+1 WHERE id=:id",
    nativeQuery = true)
    int increaseViewsById(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE article SET number_of_likes=number_of_likes+1 WHERE id=:id",
            nativeQuery = true)
    int increaseLikesById(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE article SET number_of_likes=number_of_likes-1 WHERE id=:id",
            nativeQuery = true)
    int decreaseLikesById(@Param("id") Integer id);

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

    @Query(value = "SELECT * from Article ORDER BY id DESC, :field ASC",
            nativeQuery = true)
    Page<Article> searchHotArticles(@Param("field") String field, Pageable pageable);
}
