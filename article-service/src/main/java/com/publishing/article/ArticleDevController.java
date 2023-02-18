package com.publishing.article;

import com.publishing.clients.article.Article;
import com.publishing.clients.category.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dev/api/v1")
@RequiredArgsConstructor
public class ArticleDevController {

    private final ArticleService articleService;

    @GetMapping("category/{categoryId}/articles")
    @ResponseStatus(HttpStatus.OK)
    public List<Article> getArticlesByCategory(@PathVariable("categoryId") Integer categoryId){
        return articleService.getArticlesByCategory(categoryId);
    }

    @GetMapping("users/{userId}/articles")
    @ResponseStatus(HttpStatus.OK)
    public List<Article> getArticlesByUser(@PathVariable("userId") Integer userId){
        return articleService.getArticlesByAuthor(userId);
    }

}
