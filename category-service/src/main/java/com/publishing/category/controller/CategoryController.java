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
  public List<EntityCategoryResponseDto> getCategoriesWithSort(@RequestParam("field") String field,
                                                             @RequestParam("direction") String direction){
    return categoryService.findCategoriesWithSorting(field, direction);
  }

  @GetMapping("pagination/{offset}/{pageSize}")
  @ResponseStatus(HttpStatus.OK)
  public CategoryPageResponseDto getCategoriesWithPagination(@PathVariable int offset, @PathVariable int pageSize){
    return categoryService.findCategoriesWithPagination(offset, pageSize);
  }

  @GetMapping(path = "pagination/{offset}/{pageSize}", params = {"field", "direction"})
  @ResponseStatus(HttpStatus.OK)
  public CategoryPageResponseDto getCategoriesWithPaginationAndSort(
                                                          @PathVariable int offset,
                                                          @PathVariable int pageSize,
                                                          @RequestParam("field") String field,
                                                          @RequestParam("direction") String direction){
    return categoryService.findCategoriesWithPaginationAndSorting(offset, pageSize, field, direction);
  }

  @GetMapping("search")
  @ResponseStatus(HttpStatus.OK)
  public List<EntityCategoryResponseDto> searchCategories(@RequestParam("query") String query){
    return categoryService.searchCategories(query);
  }

  @GetMapping("search/{offset}/{pageSize}")
  @ResponseStatus(HttpStatus.OK)
  public List<EntityCategoryResponseDto> searchCategories(@RequestParam("query") String query,
                                                          @PathVariable("offset") Integer offset,
                                                          @PathVariable("pageSize") Integer pageSize){
    return categoryService.searchCategoriesWithPagination(query, offset, pageSize);
  }
}