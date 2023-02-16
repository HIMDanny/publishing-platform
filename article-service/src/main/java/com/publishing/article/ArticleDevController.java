package com.publishing.article;

import com.publishing.clients.article.Article;
import com.publishing.clients.category.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dev/api/v1")
@RequiredArgsConstructor
public class ArticleDevController {

    private final ArticleService articleService;

    @GetMapping("category/{categoryId}/articles")
    public List<Article> getArticlesByCategory(@PathVariable("categoryId") Integer categoryId){
        return articleService.getArticlesByCategory(categoryId);
    }

    @GetMapping("users/{userId}/articles")
    public List<Article> getArticlesByUser(@PathVariable("userId") Integer userId){
        return articleService.getArticlesByAuthor(userId);
    }

}
