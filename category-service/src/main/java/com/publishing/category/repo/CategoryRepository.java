package com.publishing.category.repo;

import com.publishing.category.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query("SELECT c FROM Category c WHERE " +
            "c.name LIKE CONCAT('%', :query, '%')")
    List<Category> searchCategories(@Param("query") String query);

    @Query(value = "SELECT * FROM category c WHERE " +
            "c.name LIKE CONCAT('%', :query, '%') " +
            "LIMIT :pageSize OFFSET (:offset * :pageSize)",
            nativeQuery = true)
    List<Category> searchCategoriesWithPagination(@Param("query") String query,
                                                  @Param("offset") Integer offset,
                                                  @Param("pageSize") Integer pageSize);

}