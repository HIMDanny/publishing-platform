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
    public List<EntityArticleResponseDto> searchArticles(@RequestParam("query") String query){
        return articleSearchService.searchArticles(query);
    }

    @GetMapping(params = {"query", "offset", "pageSize"})
    @ResponseStatus(HttpStatus.OK)
    public List<EntityArticleResponseDto> searchArticlesWithPagination(@RequestParam("query") String query,
                                                                       @RequestParam("offset") Integer offset,
                                                                       @RequestParam("pageSize") Integer pageSize){
        return articleSearchService.searchArticlesWithPagination(query, offset, pageSize);
    }

}
