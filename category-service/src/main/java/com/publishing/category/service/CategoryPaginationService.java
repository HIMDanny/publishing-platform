package com.publishing.category.service;

import com.publishing.category.dto.CategoryPageResponseDto;
import com.publishing.category.dto.EntityCategoryResponseDto;
import com.publishing.category.model.Category;
import com.publishing.category.repo.CategoryRepository;
import com.publishing.clients.article.ArticleClient;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryPaginationService extends CategoryCommonService{

    private final CategoryRepository categoryRepository;
    private final ArticleClient articleClient;

    public CategoryPageResponseDto findCategoriesWithPagination(int offset, int pageSize){
        Page<Category> categoriesPage = categoryRepository.findAll(
                PageRequest.of(offset - 1, pageSize));

        List<Category> categories = categoriesPage.stream().collect(Collectors.toList());

        List<EntityCategoryResponseDto> categoryDtos = getListOfCategoryDTOS(categories);

        return CategoryPageResponseDto.builder()
                .totalElements(categoriesPage.getTotalElements())
                .totalPages(categoriesPage.getTotalPages())
                .page(offset)
                .pageSize(pageSize)
                .categories(categoryDtos)
                .build();
    }

    public CategoryPageResponseDto findCategoriesWithPaginationAndSorting(int offset, int pageSize, String field, String dirVal){
        Sort.Direction direction = Sort.Direction.valueOf(dirVal.toUpperCase());

        Page<Category> categoriesPage = categoryRepository.findAll(
                PageRequest.of(offset - 1, pageSize).withSort(Sort.by(direction, field)));

        List<Category> categories = categoriesPage.stream().collect(Collectors.toList());

        List<EntityCategoryResponseDto> categoryDtos = getListOfCategoryDTOS(categories);

        return CategoryPageResponseDto.builder()
                .totalElements(categoriesPage.getTotalElements())
                .totalPages(categoriesPage.getTotalPages())
                .page(offset)
                .pageSize(pageSize)
                .categories(categoryDtos)
                .build();
    }

    private List<EntityCategoryResponseDto> getListOfCategoryDTOS(List<Category> categories){
        return categories.stream()
                .peek(category -> category.setArticles(articleClient.getArticleResponsesByCategory(category.getId())))
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }
}
