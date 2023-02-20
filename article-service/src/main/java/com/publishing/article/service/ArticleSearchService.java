package com.publishing.article.service;

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

    public List<EntityArticleResponseDto> searchArticlesWithPagination(String value, Integer offset, Integer pageSize) {
        List<Article> articles = articleRepository.searchArticlesWithPagination(value, PageRequest.of(offset - 1, pageSize))
                .stream()
                .collect(Collectors.toList());

        return getListOfArticleDTOS(articles);
    }


}
