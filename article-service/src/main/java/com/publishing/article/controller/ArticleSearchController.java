package com.publishing.article.controller;

import com.publishing.article.dto.ArticlePageResponseDto;
import com.publishing.article.service.ArticleSearchService;
import com.publishing.clients.article.dto.EntityArticleResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/articles/search")
@RequiredArgsConstructor
public class ArticleSearchController {

    private final ArticleSearchService articleSearchService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<EntityArticleResponseDto> searchArticles(@RequestParam("value") String value){
        return articleSearchService.searchArticles(value);
    }

    @GetMapping(params = {"value", "page", "pageSize"})
    @ResponseStatus(HttpStatus.OK)
    public ArticlePageResponseDto searchArticlesWithPagination(@RequestParam("value") String value,
                                                               @RequestParam(value = "page", defaultValue = "1") Integer page,
                                                               @RequestParam(value = "pageSize", required = true, defaultValue = "10") Integer pageSize){
        return articleSearchService.searchArticlesWithPagination(value, page, pageSize);
    }
}
