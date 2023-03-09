package com.publishing.category.service;

import com.publishing.category.dto.CategoryPageResponseDto;
import com.publishing.clients.PaginationParameters;
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

    public CategoryPageResponseDto findCategoriesWithPaginationAndSorting(PaginationParameters params){
        Sort.Direction direction = Sort.Direction.valueOf(params.getDirection());

        Page<Category> categoriesPage = categoryRepository.findAll(
                PageRequest.of(params.getPage() - 1, params.getPageSize()).withSort(Sort.by(direction, params.getField())));

        List<Category> categories = categoriesPage.stream().collect(Collectors.toList());

        List<EntityCategoryResponseDto> categoryDtos = getListOfCategoryDTOS(categories);

        return CategoryPageResponseDto.builder()
                .totalElements(categoriesPage.getTotalElements())
                .totalPages(categoriesPage.getTotalPages())
                .page(params.getPage())
                .pageSize(params.getPageSize())
                .categories(categoryDtos)
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
}
