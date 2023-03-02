package com.publishing.article.controller;

import com.publishing.article.service.ArticleService;
import com.publishing.article.model.Article;
import com.publishing.clients.PaginationParameters;
import com.publishing.clients.article.dto.ArticlePageResponseDto;
import com.publishing.clients.article.dto.EntityArticleResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dev/api/v1")
@RequiredArgsConstructor
public class ArticleDevController {

    private final ArticleService articleService;

    @GetMapping(value = "articles", params = "userId")
    @ResponseStatus(HttpStatus.OK)
    public List<EntityArticleResponseDto> getArticleResponsesByUser(@RequestParam("userId") Integer userId){
        return articleService.getArticlesByAuthor(userId);
    }

    @GetMapping(value = "articles/pagination")
    @ResponseStatus(HttpStatus.OK)
    public ArticlePageResponseDto getArticleResponsesByCategoryWithPagination(
            @RequestParam("categoryId") Integer categoryId,
            PaginationParameters paginationParameters
    ){
        return articleService.getArticlesPageByCategory(categoryId, paginationParameters);
    }
}
