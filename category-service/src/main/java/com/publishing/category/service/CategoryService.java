package com.publishing.category.service;

import com.publishing.category.dto.EntityCategoryResponseDto;
import com.publishing.category.dto.LetterSortingCategoriesDto;
import com.publishing.clients.PaginationParameters;
import com.publishing.category.repo.CategoryRepository;
import com.publishing.category.dto.CategoryRequestDto;
import com.publishing.category.model.Category;
import com.publishing.clients.article.ArticleClient;
import com.publishing.clients.article.dto.ArticlePageResponseDto;
import com.publishing.clients.category.dto.CategoryResponseDto;
import com.publishing.exception.CategoryException;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.publishing.exception.NameNotUniqueException;
import com.publishing.util.CategoryPaginationParametersValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService extends CategoryCommonService{

  private final CategoryRepository categoryRepository;
  private final CategoryPaginationParametersValidator paramsValidator;
  private final ArticleClient articleClient;

  public List<EntityCategoryResponseDto> findCategoriesWithSorting(String field, String dirVal){

    field = paramsValidator.getCorrectValue(field);

    Sort.Direction direction = (dirVal != null && dirVal.equalsIgnoreCase("desc"))
            ? Sort.Direction.DESC : Sort.Direction.ASC;

    List<Category> categories = categoryRepository.findAll(Sort.by(direction, field));

    return getListOfCategoryDTOS(categories);
  }

  public List<EntityCategoryResponseDto> getAllCategories(){
    return getListOfCategoryDTOS(categoryRepository.findAll());
  }

  public Integer saveCategory(CategoryRequestDto categoryRequestDto) throws NameNotUniqueException {
    checkNameForUnique(categoryRequestDto.getName());
    return categoryRepository.save(Category.builder()
        .name(categoryRequestDto.getName())
        .build()).getId();
  }

  public EntityCategoryResponseDto updateCategory(Integer id, CategoryRequestDto categoryRequestDto) throws CategoryException, NameNotUniqueException {
    Category foundCategoryInDb = categoryRepository.findById(id)
        .orElseThrow(
            () -> new CategoryException(String.format("Category with id %d cannot be found", id)));

    if(categoryRequestDto == null || categoryRequestDto.getName().isBlank())
      return mapToDto(foundCategoryInDb);

    foundCategoryInDb.setName(categoryRequestDto.getName());
    checkNameForUnique(foundCategoryInDb.getName());
    Category updatedCategory = categoryRepository.save(foundCategoryInDb);
    updatedCategory.setPage(articleClient.getArticleResponsesByCategoryWithPagination(
            id,
            toMap(PaginationParameters.builder()
                    .page(1).pageSize(10).field("numberOfViews").direction("asc").build())
    ));
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

  private List<EntityCategoryResponseDto> getListOfCategoryDTOS(List<Category> categories){
    return categories.stream()
            .peek(category -> category.setPage(articleClient.getArticleResponsesByCategoryWithPagination(
                    category.getId(),
                    toMap(PaginationParameters.builder()
                            .page(1).pageSize(10).field("numberOfViews").direction("asc").build())
            )))
            .map(this::mapToDto)
            .collect(Collectors.toList());
  }

    public EntityCategoryResponseDto getCategory(Integer id, PaginationParameters params) throws CategoryException {

      params.setField(paramsValidator.getCorrectValue(params.getField()));

      Category category = categoryRepository.findById(id)
              .orElseThrow(() -> new CategoryException(String.format("Category with id %d cannot be found", id)));

      ArticlePageResponseDto articlesOfCategory = articleClient.getArticleResponsesByCategoryWithPagination(id, toMap(params));

      category.setPage(articlesOfCategory);
      return mapToDto(category);
    }

  public List<LetterSortingCategoriesDto> getCategoriesDividedByLetters() {

    List<LetterSortingCategoriesDto> mapWithCategoriesByLetter = new ArrayList<>();

    List<Category> sourceList = categoryRepository.findAll(Sort.by("name"));


    for(int i = 0; i < sourceList.size(); i++) {
      List<CategoryResponseDto> categoryResponseDtoList = new ArrayList<>();
      char letter = sourceList.get(i).getName().charAt(0);
      for(int j = i; j < sourceList.size(); j++){
        Category currentCategory = sourceList.get(j);
        if(currentCategory.getName().charAt(0) == letter){
          categoryResponseDtoList.add(new CategoryResponseDto(currentCategory.getId(), currentCategory.getName()));
        } else {
          i = j - 1;
          break;
        }
      }
      mapWithCategoriesByLetter.add(new LetterSortingCategoriesDto(letter, categoryResponseDtoList));
    }
    return mapWithCategoriesByLetter;
  }

  private void checkNameForUnique(String name) throws NameNotUniqueException {
    if(categoryRepository.findByName(name).isPresent()){
      throw new NameNotUniqueException(String.format("Category with name %s is already exist", name));
    }
  }
}