package com.publishing.category.controller;

import com.publishing.category.dto.EntityCategoryResponseDto;
import com.publishing.category.dto.LetterSortingCategoriesDto;
import com.publishing.clients.PaginationParameters;
import com.publishing.category.service.CategoryService;
import com.publishing.category.dto.CategoryRequestDto;
import com.publishing.clients.category.dto.CategoryResponseDto;
import com.publishing.exception.CategoryException;
import java.util.List;
import java.util.Map;

import jakarta.validation.Valid;
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
    return categoryService.getCategory(id, PaginationParameters.builder().field("numberOfViews").build());
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<EntityCategoryResponseDto> getAllCategories(){
    return categoryService.getAllCategories();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Integer saveCategory(@RequestBody @Valid CategoryRequestDto categoryRequestDto){
    return categoryService.saveCategory(categoryRequestDto);
  }

  @PutMapping("{id}")
  @ResponseStatus(HttpStatus.OK)
  public EntityCategoryResponseDto updateCategory(@PathVariable("id") Integer id, @RequestBody(required = false) CategoryRequestDto categoryRequestDto)
      throws CategoryException {
    return categoryService.updateCategory(id, categoryRequestDto);
  }

  @DeleteMapping("{id}")
  @ResponseStatus(HttpStatus.OK)
  public boolean deleteCategory(@PathVariable("id") Integer id) throws CategoryException {
    return categoryService.deleteCategoryById(id);
  }

  @GetMapping(params = "field")
  @ResponseStatus(HttpStatus.OK)
  public List<EntityCategoryResponseDto> getCategoriesWithSort(
                              @RequestParam(value = "field") String field,
                              @RequestParam(value = "direction", required = false) String direction){
    return categoryService.findCategoriesWithSorting(field, direction);
  }

  @GetMapping("{id}/articles")
  @ResponseStatus(HttpStatus.OK)
  public EntityCategoryResponseDto getCategoryArticles(@PathVariable("id") Integer id,
                                                       PaginationParameters paginationParameters) throws CategoryException {
    return categoryService.getCategory(id, paginationParameters);
  }

  @GetMapping("letters")
  @ResponseStatus(HttpStatus.OK)
  public List<LetterSortingCategoriesDto> getCategoriesDividedByLetters(){
    return categoryService.getCategoriesDividedByLetters();
  }
}