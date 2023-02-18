package com.publishing.category;

import com.publishing.clients.category.Category;
import com.publishing.exception.CategoryException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

  private final CategoryService categoryService;

  @GetMapping("{id}")
  @ResponseStatus(HttpStatus.OK)
  public Category getCategoryById(@PathVariable("id") Integer id) throws CategoryException {
    // TODO: handle exception
    return categoryService.getCategoryById(id);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<Category> getAllCategories(){
    return categoryService.getAllCategories();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Category saveCategory(@RequestBody CategoryRequest categoryRequest){
    return categoryService.saveCategory(categoryRequest);
  }

  @PutMapping("{id}")
  @ResponseStatus(HttpStatus.OK)
  public Category updateCategory(@PathVariable("id") Integer id, @RequestBody CategoryRequest categoryRequest)
      throws CategoryException {
    // TODO: handle exception
    return categoryService.updateCategory(id, categoryRequest);
  }

  @DeleteMapping("{id}")
  @ResponseStatus(HttpStatus.OK)
  public void deleteCategory(@PathVariable("id") Integer id) throws CategoryException {
    categoryService.deleteCategoryById(id);
  }
}