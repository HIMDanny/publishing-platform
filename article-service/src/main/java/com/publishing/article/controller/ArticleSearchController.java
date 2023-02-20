package com.publishing.article.controller;

import com.publishing.article.service.ArticleSearchService;
import com.publishing.article.service.ArticleService;
import com.publishing.clients.article.dto.EntityArticleResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/search")
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
    public List<EntityArticleResponseDto> searchArticlesWithPagination(@RequestParam("value") String value,
                                                                       @RequestParam(value = "page", defaultValue = "1") Integer page,
                                                                       @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize){
        return articleSearchService.searchArticlesWithPagination(value, page, pageSize);
    }
}
