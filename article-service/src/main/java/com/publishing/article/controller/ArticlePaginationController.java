package com.publishing.article.controller;

import com.publishing.clients.article.dto.ArticlePageResponseDto;
import com.publishing.clients.PaginationParameters;
import com.publishing.article.service.ArticlePaginationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/articles/pagination")
@RequiredArgsConstructor
public class ArticlePaginationController {

    private final ArticlePaginationService articlePaginationService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ArticlePageResponseDto getArticlesWithPagination(PaginationParameters params){
        return articlePaginationService.findArticlesWithPaginationAndSorting(params);
    }
}
