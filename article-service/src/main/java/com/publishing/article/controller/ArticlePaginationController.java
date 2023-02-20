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
    public ArticlePageResponseDto getArticlesWithPagination(@RequestParam(value = "offset", defaultValue = "1") Integer offset,
                                                            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize){
        return articlePaginationService.findArticlesWithPagination(offset, pageSize);
    }

    @GetMapping(params = {"offset", "pageSize", "field", "direction"})
    @ResponseStatus(HttpStatus.OK)
    public ArticlePageResponseDto getArticlesWithPaginationAndSort(
                                    @RequestParam(value = "offset", defaultValue = "1") Integer offset,
                                    @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                    @RequestParam(value = "field", defaultValue = "ASC") String field,
                                    @RequestParam(value = "direction", defaultValue = "ASC") String direction){
        return articlePaginationService.findArticlesWithPaginationAndSorting(offset, pageSize, field, direction);
    }
}
