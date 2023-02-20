package com.publishing.article.controller;

import com.publishing.article.dto.ArticlePageResponseDto;
import com.publishing.article.service.ArticlePaginationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/articles/pagination")
@RequiredArgsConstructor
public class ArticlePaginationController {

    private final ArticlePaginationService articlePaginationService;

    @GetMapping(params = {"offset", "pageSize"})
    @ResponseStatus(HttpStatus.OK)
    public ArticlePageResponseDto getArticlesWithPagination(@RequestParam("offset") int offset,
                                                            @RequestParam("pageSize") int pageSize){
        return articlePaginationService.findArticlesWithPagination(offset, pageSize);
    }

    @GetMapping(params = {"offset", "pageSize", "field", "direction"})
    @ResponseStatus(HttpStatus.OK)
    public ArticlePageResponseDto getArticlesWithPaginationAndSort(
            @RequestParam("offset") int offset,
            @RequestParam("pageSize") int pageSize,
            @RequestParam("field") String field,
            @RequestParam("direction") String direction){
        return articlePaginationService.findArticlesWithPaginationAndSorting(offset, pageSize, field, direction);
    }

}
