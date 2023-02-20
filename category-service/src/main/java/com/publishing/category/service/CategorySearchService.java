package com.publishing.category.service;

import com.publishing.category.dto.EntityCategoryResponseDto;
import com.publishing.category.repo.CategoryRepository;
import com.publishing.clients.article.ArticleClient;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategorySearchService extends CategoryCommonService{

    private final CategoryRepository categoryRepository;
    private final ArticleClient articleClient;

    public List<EntityCategoryResponseDto> searchCategories(String query){
        return categoryRepository.searchCategories(query).stream()
                .peek(category -> category.setArticles(articleClient.getArticleResponsesByCategory(category.getId())))
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public List<EntityCategoryResponseDto> searchCategoriesWithPagination(String query, Integer offset, Integer pageSize){
        return categoryRepository.searchCategoriesWithPagination(query, PageRequest.of(offset, pageSize)).stream()
                .peek(category -> category.setArticles(articleClient.getArticleResponsesByCategory(category.getId())))
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

}
