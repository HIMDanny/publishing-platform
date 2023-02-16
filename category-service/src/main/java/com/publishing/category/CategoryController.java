package com.publishing.category;

import com.publishing.clients.category.Category;
import com.publishing.exception.CategoryException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

  private final CategoryService categoryService;

  @GetMapping("{id}")
  public Category getCategoryById(@PathVariable("id") Integer id) throws CategoryException {
    // TODO: handle exception
    return categoryService.getCategoryById(id);
  }

  @GetMapping
  public List<Category> getAllCategories(){
    return categoryService.getAllCategories();
  }

  @PostMapping
  public Category saveCategory(@RequestBody CategoryRequest categoryRequest){
    return categoryService.saveCategory(categoryRequest);
  }

  @PutMapping("{id}")
  public Category updateCategory(@PathVariable("id") Integer id, @RequestBody CategoryRequest categoryRequest)
      throws CategoryException {
    // TODO: handle exception
    return categoryService.updateCategory(id, categoryRequest);
  }

  @DeleteMapping("{id}")
  public void deleteCategory(@PathVariable("id") Integer id) throws CategoryException {
    categoryService.deleteCategoryById(id);
  }
}