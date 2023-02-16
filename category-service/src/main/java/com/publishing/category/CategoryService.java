package com.publishing.category;

import com.publishing.clients.article.ArticleClient;
import com.publishing.clients.category.Category;
import com.publishing.clients.category.CategoryResponse;
import com.publishing.exception.CategoryException;
import java.util.List;
import javax.xml.catalog.CatalogException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

  private final CategoryRepository categoryRepository;
  private final ArticleClient articleClient;

  public Category getCategoryById(Integer id) throws CategoryException {
    Category category = categoryRepository.findById(id)
            .orElseThrow(() -> new CategoryException(String.format("Category with id %d cannot be found", id)));
    category.setArticles(articleClient.getArticlesByCategory(id));
    return category;
  }

  public List<Category> getAllCategories(){
    return categoryRepository.findAll();
  }

  public Category saveCategory(CategoryRequest categoryRequest){
    return categoryRepository.save(Category.builder()
        .name(categoryRequest.name())
        .build());
  }

  public Category updateCategory(Integer id, CategoryRequest categoryRequest) throws CategoryException {
    Category foundCategoryInDb = categoryRepository.findById(id)
        .orElseThrow(
            () -> new CategoryException(String.format("Category with id %d cannot be found", id)));

    foundCategoryInDb.setName(categoryRequest.name());
    return categoryRepository.save(foundCategoryInDb);
  }

  public void deleteCategoryById(Integer id) throws CategoryException {
    Category foundCategoryInDb = categoryRepository.findById(id)
        .orElseThrow(
            () -> new CategoryException(String.format("Category with id %d cannot be found", id)));
    categoryRepository.delete(foundCategoryInDb);
  }

    public CategoryResponse getCategoryResponse(Integer id) throws CategoryException {
      Category category = categoryRepository.findById(id)
              .orElseThrow(() -> new CategoryException(String.format("Category with id %d cannot be found", id)));
      return CategoryResponse.builder()
              .id(category.getId())
              .name(category.getName())
              .build();
    }
}