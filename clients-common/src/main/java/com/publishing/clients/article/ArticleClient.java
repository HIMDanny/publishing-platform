package com.publishing.clients.article;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(
        name = "article-service",
        path = "localhost:8086"
)
public interface ArticleClient {

    @GetMapping("/dev/api/v1/category/{categoryId}/articles")
    List<Article> getArticlesByCategory(@PathVariable("categoryId") Integer categoryId);
}