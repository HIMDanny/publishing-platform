package com.publishing.article.service;

import com.publishing.article.dto.ArticlePageResponseDto;
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

    public ArticlePageResponseDto findArticlesWithPagination(int offset, int pageSize){
        Page<Article> pageOfArticles = articleRepository.findAll(PageRequest.of(offset - 1, pageSize));

        List<Article> articles = pageOfArticles.stream().collect(Collectors.toList());

        List<EntityArticleResponseDto> articleDtos = getListOfArticleDTOS(articles);

        return ArticlePageResponseDto.builder()
                .totalElements(pageOfArticles.getTotalElements())
                .totalPages(pageOfArticles.getTotalPages())
                .page(offset)
                .pageSize(pageSize)
                .articles(articleDtos)
                .build();
    }

    public ArticlePageResponseDto findArticlesWithPaginationAndSorting(int offset, int pageSize, String field, String dirVal){
        Sort.Direction direction = Sort.Direction.valueOf(dirVal.toUpperCase());
        Page<Article> pageOfArticles = articleRepository.findAll(
                PageRequest.of(offset - 1, pageSize).withSort(Sort.by(direction, field)));

        List<Article> articles = pageOfArticles.stream().collect(Collectors.toList());

        List<EntityArticleResponseDto> articleDtos = getListOfArticleDTOS(articles);

        return ArticlePageResponseDto.builder()
                .totalElements(pageOfArticles.getTotalElements())
                .totalPages(pageOfArticles.getTotalPages())
                .page(offset)
                .pageSize(pageSize)
                .articles(articleDtos)
                .build();
    }

}
