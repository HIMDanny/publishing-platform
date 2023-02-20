package com.publishing.article.service;

import com.publishing.article.model.Article;
import com.publishing.clients.article.dto.EntityArticleResponseDto;
import com.publishing.clients.category.CategoryClient;
import com.publishing.clients.category.dto.CategoryResponseDto;
import com.publishing.clients.user.UserClient;
import com.publishing.clients.user.dto.UserResponseDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class ArticleCommonService {

    @Autowired
    private CategoryClient categoryClient;
    @Autowired
    private UserClient userClient;

    protected EntityArticleResponseDto mapToArticleDTO(Article article) {
        return EntityArticleResponseDto.builder()
                .id(article.getId())
                .title(article.getTitle())
                .content(article.getContent())
                .mainImagePath(article.getMainImagePath())
                .publishingDate(article.getPublishingDate())
                .minutesToRead(article.getMinutesToRead())
                .numberOfViews(article.getNumberOfViews())
                .numberOfLikes(article.getNumberOfLikes())
                .category(article.getCategory())
                .author(article.getAuthor())
                .build();
    }

    protected List<EntityArticleResponseDto> getListOfArticleDTOS(List<Article> articles) {

        Map<Integer, CategoryResponseDto> categories = getMapWithCategoryIds(articles);

        Map<Integer, UserResponseDto> authors = getMapWithUserIds(articles);

        return articles.stream()
                .peek(article -> article.setCategory(categories.get(article.getCategoryId())))
                .peek(article -> article.setAuthor(authors.get(article.getAuthorId())))
                .map(this::mapToArticleDTO)
                .collect(Collectors.toList());
    }

    protected Map<Integer, UserResponseDto> getMapWithUserIds(List<Article> articles) {
        List<Integer> authorIds = new ArrayList<>();
        return articles.stream()
                .filter(article -> !authorIds.contains(article.getAuthorId()))
                .peek(article -> authorIds.add(article.getAuthorId()))
                .map(article -> userClient.getUserResponse(article.getAuthorId()))
                .collect(Collectors.toMap(UserResponseDto::getId, Function.identity()));
    }

    protected Map<Integer, CategoryResponseDto> getMapWithCategoryIds(List<Article> articles) {
        List<Integer> categoriesIds = new ArrayList<>();
        return articles.stream()
                .filter(article -> !categoriesIds.contains(article.getCategoryId()))
                .peek(article -> categoriesIds.add(article.getCategoryId()))
                .map(article -> categoryClient.getCategoryResponse(article.getCategoryId()))
                .map(categoryResponseDto -> CategoryResponseDto.builder().id(categoryResponseDto.getId()).name(categoryResponseDto.getName()).build())
                .collect(Collectors.toMap(CategoryResponseDto::getId, Function.identity()));
    }

}
