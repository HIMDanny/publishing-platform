package com.publishing.article.controller;

import com.publishing.article.service.ArticleService;
import com.publishing.clients.PaginationParameters;
import com.publishing.clients.article.dto.ArticlePageResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dev/api/v1")
@RequiredArgsConstructor
public class ArticleDevController {

    private final ArticleService articleService;

    @GetMapping(value = "articles/pagination")
    @ResponseStatus(HttpStatus.OK)
    public ArticlePageResponseDto getArticleResponsesByCategoryWithPagination(
            @RequestParam("categoryId") Integer categoryId,
            PaginationParameters paginationParameters
    ){
        return articleService.getArticlesPageByCategory(categoryId, paginationParameters);
    }

    @GetMapping(value = "articles/pagination")
    @ResponseStatus(HttpStatus.OK)
    public ArticlePageResponseDto getArticleResponsesByUserWithPagination(
            @RequestParam("userId") Integer userId,
            PaginationParameters paginationParameters
    ){
        return articleService.getArticlesPageByAuthor(userId, paginationParameters);
    }

}
