package com.publishing.article.service;

import com.publishing.clients.article.dto.ArticlePageResponseDto;
import com.publishing.clients.PaginationParameters;
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
public class ArticlePaginationService extends ArticleCommonService{

    private final ArticleRepository articleRepository;

    public ArticlePageResponseDto findArticlesWithPaginationAndSorting(PaginationParameters params){
        Sort.Direction direction = Sort.Direction.valueOf(params.getDirection());

        Page<Article> pageOfArticles = articleRepository.findAll(
                PageRequest.of(params.getPage() - 1, params.getPageSize()).withSort(Sort.by(direction, params.getField())));

        List<Article> articles = pageOfArticles.stream().collect(Collectors.toList());

        List<EntityArticleResponseDto> articleDtos = getListOfArticleDTOS(articles);

        return ArticlePageResponseDto.builder()
                .totalElements(pageOfArticles.getTotalElements())
                .totalPages(pageOfArticles.getTotalPages())
                .page(params.getPage())
                .pageSize(params.getPageSize())
                .articles(articleDtos)
                .build();
    }

}
