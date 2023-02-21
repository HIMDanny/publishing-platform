package com.publishing.article.service;

import com.publishing.article.dto.ArticlePageResponseDto;
import com.publishing.article.dto.ArticlePaginationParameters;
import com.publishing.article.model.Article;
import com.publishing.article.repo.ArticleRepository;
import com.publishing.clients.article.dto.EntityArticleResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleSearchService extends ArticleCommonService{

    private final ArticleRepository articleRepository;

    public List<EntityArticleResponseDto> searchArticles(String value, String fieldVal, String dirVal){
        Sort.Direction direction = (dirVal != null && dirVal.equalsIgnoreCase("desc"))
                ? Sort.Direction.DESC : Sort.Direction.ASC;

        String field = fieldVal == null ? "id" : fieldVal;

        List<Article> articles = articleRepository.searchArticles(value, Sort.by(direction, field));
        return getListOfArticleDTOS(articles);
    }

    public ArticlePageResponseDto searchArticlesWithPagination(String value, ArticlePaginationParameters params) {
        Sort.Direction direction = Sort.Direction.valueOf(params.getDirection());

        Page<Article> articlesPage = articleRepository.searchArticlesWithPagination(
                value, PageRequest.of(params.getPage() - 1, params.getPageSize()).withSort(direction, params.getField()));

        List<Article> articlesList = articlesPage.stream().collect(Collectors.toList());

        List<EntityArticleResponseDto> articles = getListOfArticleDTOS(articlesList);

        return ArticlePageResponseDto.builder()
                .totalPages(articlesPage.getTotalPages())
                .totalElements(articlesPage.getTotalElements())
                .page(params.getPage())
                .pageSize(params.getPageSize())
                .articles(articles)
                .build();
    }
}
