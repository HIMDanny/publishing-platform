package com.publishing.category.service;

import com.publishing.category.dto.EntityCategoryResponseDto;
import com.publishing.category.repo.CategoryRepository;
import com.publishing.category.dto.CategoryRequestDto;
import com.publishing.category.model.Category;
import com.publishing.clients.article.ArticleClient;
import com.publishing.clients.category.dto.CategoryResponseDto;
import com.publishing.exception.CategoryException;
import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

  private final CategoryRepository categoryRepository;
  private final ArticleClient articleClient;

  public EntityCategoryResponseDto getCategoryById(Integer id) throws CategoryException {
    Category category = categoryRepository.findById(id)
            .orElseThrow(() -> new CategoryException(String.format("Category with id %d cannot be found", id)));
    category.setArticles(articleClient.getArticleResponsesByCategory(id));
    return mapToDto(category);
  }

  public List<EntityCategoryResponseDto> getAllCategories(){
    return categoryRepository.findAll().stream()
            .peek(category -> category.setArticles(articleClient.getArticleResponsesByCategory(category.getId())))
            .map(this::mapToDto)
            .collect(Collectors.toList());
  }

  public List<EntityCategoryResponseDto> searchCategories(String query){
    return categoryRepository.searchCategories(query).stream()
            .map(this::mapToDto)
            .collect(Collectors.toList());
  }

  public List<EntityCategoryResponseDto> searchCategoriesWithPagination(String query, Integer offset, Integer pageSize){
    return categoryRepository.searchCategoriesWithPagination(query, offset, pageSize).stream()
            .map(this::mapToDto)
            .collect(Collectors.toList());
  }

  public Integer saveCategory(CategoryRequestDto categoryRequestDto){
    // TODO check if there is category with this name
    return categoryRepository.save(Category.builder()
        .name(categoryRequestDto.getName())
        .build()).getId();
  }

  public EntityCategoryResponseDto updateCategory(Integer id, CategoryRequestDto categoryRequestDto) throws CategoryException {
    Category foundCategoryInDb = categoryRepository.findById(id)
        .orElseThrow(
            () -> new CategoryException(String.format("Category with id %d cannot be found", id)));

    foundCategoryInDb.setName(categoryRequestDto.getName());
    Category updatedCategory = categoryRepository.save(foundCategoryInDb);
    updatedCategory.setArticles(articleClient.getArticleResponsesByCategory(foundCategoryInDb.getId()));
    return mapToDto(updatedCategory);
  }

  public boolean deleteCategoryById(Integer id) throws CategoryException {
    Category foundCategoryInDb = categoryRepository.findById(id)
        .orElseThrow(
            () -> new CategoryException(String.format("Category with id %d cannot be found", id)));
    categoryRepository.delete(foundCategoryInDb);
    return true;
  }

    public CategoryResponseDto getCategoryResponse(Integer id) throws CategoryException {
      Category category = categoryRepository.findById(id)
              .orElseThrow(() -> new CategoryException(String.format("Category with id %d cannot be found", id)));
      return CategoryResponseDto.builder()
              .id(category.getId())
              .name(category.getName())
              .build();
    }

  private EntityCategoryResponseDto mapToDto(Category category) {
    return EntityCategoryResponseDto.builder()
            .id(category.getId())
            .name(category.getName())
            .articles(category.getArticles())
            .build();
  }
}