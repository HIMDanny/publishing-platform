package com.publishing.article.service;

import com.publishing.article.dto.ArticlePageResponseDto;
import com.publishing.article.model.Article;
import com.publishing.article.repo.ArticleRepository;
import com.publishing.clients.article.dto.EntityArticleResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleSearchService extends ArticleCommonService{

    private final ArticleRepository articleRepository;

    public List<EntityArticleResponseDto> searchArticles(String value){
        List<Article> articles = articleRepository.searchArticles(value);
        return getListOfArticleDTOS(articles);
    }

    public ArticlePageResponseDto searchArticlesWithPagination(String value, Integer offset, Integer pageSize) {
        Page<Article> articlesPage = articleRepository.searchArticlesWithPagination(value, PageRequest.of(offset - 1, pageSize));

        List<Article> articlesList = articlesPage.stream().collect(Collectors.toList());

        List<EntityArticleResponseDto> articles = getListOfArticleDTOS(articlesList);

        return ArticlePageResponseDto.builder()
                .totalPages(articlesPage.getTotalPages())
                .totalElements(articlesPage.getTotalElements())
                .page(offset)
                .pageSize(pageSize)
                .articles(articles)
                .build();
    }


}
