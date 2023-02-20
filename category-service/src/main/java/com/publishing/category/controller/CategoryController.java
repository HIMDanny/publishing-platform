package com.publishing.category.controller;

import com.publishing.category.dto.CategoryPageResponseDto;
import com.publishing.category.dto.EntityCategoryResponseDto;
import com.publishing.category.service.CategoryService;
import com.publishing.category.dto.CategoryRequestDto;
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
  public EntityCategoryResponseDto getCategoryById(@PathVariable("id") Integer id) throws CategoryException {
    // TODO: handle exception
    return categoryService.getCategoryById(id);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<EntityCategoryResponseDto> getAllCategories(){
    return categoryService.getAllCategories();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Integer saveCategory(@RequestBody CategoryRequestDto categoryRequestDto){
    return categoryService.saveCategory(categoryRequestDto);
  }

  @PutMapping("{id}")
  @ResponseStatus(HttpStatus.OK)
  public EntityCategoryResponseDto updateCategory(@PathVariable("id") Integer id, @RequestBody CategoryRequestDto categoryRequestDto)
      throws CategoryException {
    // TODO: handle exception
    return categoryService.updateCategory(id, categoryRequestDto);
  }

  @DeleteMapping("{id}")
  @ResponseStatus(HttpStatus.OK)
  public boolean deleteCategory(@PathVariable("id") Integer id) throws CategoryException {
    return categoryService.deleteCategoryById(id);
  }

  @GetMapping(params = {"field", "direction"})
  @ResponseStatus(HttpStatus.OK)
  public List<EntityCategoryResponseDto> getCategoriesWithSort(
                              @RequestParam(name = "field", defaultValue = "ASC") String field,
                              @RequestParam(name = "direction", defaultValue = "ASC") String direction){
    return categoryService.findCategoriesWithSorting(field, direction);
  }
}