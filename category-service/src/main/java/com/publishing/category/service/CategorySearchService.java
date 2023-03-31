package com.publishing.category.service;

import com.publishing.category.dto.CategoryPageResponseDto;
import com.publishing.clients.PaginationParameters;
import com.publishing.category.dto.EntityCategoryResponseDto;
import com.publishing.category.model.Category;
import com.publishing.category.repo.CategoryRepository;
import com.publishing.clients.article.ArticleClient;
import com.publishing.util.CategoryPaginationParametersValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategorySearchService extends CategoryCommonService{

    private final CategoryRepository categoryRepository;
    private final CategoryPaginationParametersValidator paramsValidator;
    private final ArticleClient articleClient;

    public List<EntityCategoryResponseDto> searchCategories(String value, String fieldVal, String dirVal){

        fieldVal = (fieldVal == null) ? "id" : fieldVal;
        String field = paramsValidator.getCorrectValue(fieldVal);


        Sort.Direction direction = (dirVal != null && dirVal.equalsIgnoreCase("desc"))
                ? Sort.Direction.DESC : Sort.Direction.ASC;


        return categoryRepository.searchCategories(value, Sort.by(direction, field)).stream()
                .peek(category -> category.setPage(articleClient.getArticleResponsesByCategoryWithPagination(
                        category.getId(),
                        toMap(PaginationParameters.builder()
                                .page(1).pageSize(10).field("numberOfViews").direction("asc").build())
                )))
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public CategoryPageResponseDto searchCategoriesWithPagination(String value, PaginationParameters params){

        params.setField(paramsValidator.getCorrectValue(params.getField()));

        Sort.Direction direction = Sort.Direction.valueOf(params.getDirection());

        Page<Category> categoriesPage = categoryRepository.searchCategoriesWithPagination(
                value, PageRequest.of(params.getPage() - 1, params.getPageSize()).withSort(direction, params.getField()));

        List<EntityCategoryResponseDto> categories = categoriesPage.stream()
                .peek(category -> category.setPage(articleClient.getArticleResponsesByCategoryWithPagination(
                        category.getId(),
                        toMap(PaginationParameters.builder()
                                .page(1).pageSize(10).field("numberOfViews").direction("asc").build())
                )))
                .map(this::mapToDto)
                .collect(Collectors.toList());
        return CategoryPageResponseDto.builder()
                .totalElements(categoriesPage.getTotalElements())
                .totalPages(categoriesPage.getTotalPages())
                .page(params.getPage())
                .pageSize(params.getPageSize())
                .categories(categories)
                .build();
    }

}
