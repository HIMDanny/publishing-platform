package com.publishing.clients.article;

import com.publishing.clients.PaginationParameters;
import com.publishing.clients.article.dto.ArticlePageResponseDto;
import com.publishing.clients.article.dto.EntityArticleResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@FeignClient(
        name = "article-service",
        url = "localhost:8086"
)
public interface ArticleClient {
    @GetMapping(value = "/dev/api/v1/articles", params = "categoryId")
    @ResponseStatus(HttpStatus.OK)
    public List<EntityArticleResponseDto> getArticleResponsesByCategory(@RequestParam("categoryId") Integer categoryId);

    @GetMapping(value = "/dev/api/v1/articles", params = "userId")
    @ResponseStatus(HttpStatus.OK)
    public List<EntityArticleResponseDto> getArticleResponsesByUser(@RequestParam("userId") Integer userId);

    @GetMapping(value = "/dev/api/v1/articles/pagination")
    @ResponseStatus(HttpStatus.OK)
    public ArticlePageResponseDto getArticleResponsesByCategoryWithPagination(
            @RequestParam("categoryId") Integer categoryId,
            PaginationParameters paginationParameters
    );
}