package com.publishing.category;

import com.publishing.exception.CategoryException;
import java.util.List;
import javax.xml.catalog.CatalogException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

  private final CategoryRepository categoryRepository;

  public Category getCategoryById(Integer id) throws CategoryException {
    return categoryRepository.findById(id)
        .orElseThrow(() -> new CategoryException(String.format("Category with id %d cannot be found", id)));
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

  public void deleteCategoryById(Integer id){
    Category foundCategoryInDb = categoryRepository.findById(id)
        .orElseThrow(
            () -> new CatalogException(String.format("Category with id %d cannot be found", id)));
    categoryRepository.delete(foundCategoryInDb);
  }

}