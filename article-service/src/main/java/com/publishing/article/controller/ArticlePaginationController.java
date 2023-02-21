package com.publishing.article.controller;

import com.publishing.article.dto.ArticlePageResponseDto;
import com.publishing.article.dto.ArticlePaginationParameters;
import com.publishing.article.service.ArticlePaginationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/articles/pagination")
@RequiredArgsConstructor
public class ArticlePaginationController {

    private final ArticlePaginationService articlePaginationService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ArticlePageResponseDto getArticlesWithPagination(@RequestParam ArticlePaginationParameters params){
        return articlePaginationService.findArticlesWithPaginationAndSorting(params);
    }
}
