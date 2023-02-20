package com.publishing.article.service;

import com.publishing.article.repo.ArticleRepository;
import com.publishing.clients.article.dto.EntityArticleResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleSearchService extends ArticleCommonService{

    private final ArticleRepository articleRepository;

    public List<EntityArticleResponseDto> searchArticles(String query){
        return articleRepository.searchArticles(query).stream()
                .map(this::mapToArticleDTO)
                .collect(Collectors.toList());
    }

    public List<EntityArticleResponseDto> searchArticlesWithPagination(String query, Integer offset, Integer pageSize) {
        return articleRepository.searchArticlesWithPagination(query, offset, pageSize).stream()
                .map(this::mapToArticleDTO)
                .collect(Collectors.toList());
    }


}
