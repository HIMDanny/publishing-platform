package com.publishing.article;

import com.publishing.clients.article.Article;
import com.publishing.exception.ArticleException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/articles")
@RequiredArgsConstructor
public class ArticleController {

  private final ArticleService articleService;

  @GetMapping("{id}")
  @ResponseStatus(HttpStatus.OK)
  public Article getArticleById(@PathVariable("id") Integer id) throws ArticleException {
    // TODO: handle exception
    return articleService.getArticle(id);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<Article> getAllArticles(){
    return articleService.getAllArticles();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Article saveArticle(@RequestBody ArticleRequest articleRequest){
    return articleService.saveArticle(articleRequest);
  }

  @PutMapping("{id}")
  @ResponseStatus(HttpStatus.OK)
  public Article updateArticle(@PathVariable("id") Integer id, @RequestBody ArticleRequest articleRequest)
      throws ArticleException {
    // TODO: handle exception

    return articleService.updateArticle(id, articleRequest);
  }

  @DeleteMapping("{id}")
  @ResponseStatus(HttpStatus.OK)
  public void deleteArticle(@PathVariable("id") Integer id) throws ArticleException {
    // TODO: handle exception

    articleService.deleteArticle(id);
  }

}
