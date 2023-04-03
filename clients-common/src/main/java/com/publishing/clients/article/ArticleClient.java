package com.publishing.clients.article;

import com.publishing.clients.article.dto.ArticlePageResponseDto;
import com.publishing.clients.article.dto.EntityArticleResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Map;

@FeignClient("article")
public interface ArticleClient {
    @GetMapping(value = "/dev/api/v1/articles", params = "categoryId")
    @ResponseStatus(HttpStatus.OK)
    public List<EntityArticleResponseDto> getArticleResponsesByCategory(@RequestParam("categoryId") Integer categoryId);

    @GetMapping(value = "/dev/api/v1/articles", params = "userId")
    @ResponseStatus(HttpStatus.OK)
    public List<EntityArticleResponseDto> getArticleResponsesByUser(@RequestParam("userId") Integer userId);

    @GetMapping(value = "/dev/api/v1/articles/pagination",
            params = {"categoryId", "field", "page", "pageSize", "direction"})
    @ResponseStatus(HttpStatus.OK)
    public ArticlePageResponseDto getArticleResponsesByCategoryWithPagination(
            @RequestParam("categoryId") Integer categoryId,
            @RequestParam Map<String, String> paginationParameters
    );

    @GetMapping(value = "/dev/api/v1/articles/pagination",
            params = {"userId", "field", "page", "pageSize", "direction"})
    @ResponseStatus(HttpStatus.OK)
    public ArticlePageResponseDto getArticleResponsesByUserWithPagination(
            @RequestParam("userId") Integer userId,
            @RequestParam Map<String, String> paginationParameters
    );


    @GetMapping(value = "/dev/api/v1/articles/likes",
            params = {"userId", "field", "page", "pageSize", "direction"})
    @ResponseStatus(HttpStatus.OK)
    ArticlePageResponseDto getLikedArticlesByUserWithPagination(
            @RequestParam("userId") Integer userId,
            @RequestParam Map<String, String> paginationParameters);

    @GetMapping(value = "/dev/api/v1/articles/bookmarks",
            params = {"userId", "field", "page", "pageSize", "direction"})
    @ResponseStatus(HttpStatus.OK)
    ArticlePageResponseDto getBookmarkedArticlesByUserWithPagination(
            @RequestParam("userId") Integer userId,
            @RequestParam Map<String, String> paginationParameters);
}