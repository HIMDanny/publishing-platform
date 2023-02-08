package com.publishing.article;

import com.publishing.exception.ArticleException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/articles")
@RequiredArgsConstructor
public class ArticleController {

  private final ArticleService articleService;

  @GetMapping("{id}")
  public Article getArticleById(@PathVariable("id") Integer id) throws ArticleException {
    // TODO: handle exception
    return articleService.getArticle(id);
  }

  @GetMapping
  public List<Article> getAllArticles(){
    return articleService.getAllArticles();
  }

  @PostMapping
  public Article saveArticle(@RequestBody ArticleRequest articleRequest){
    return articleService.saveArticle(articleRequest);
  }

  @PutMapping("{id}")
  public Article updateArticle(@PathVariable("id") Integer id, @RequestBody ArticleRequest articleRequest)
      throws ArticleException {
    // TODO: handle exception

    return articleService.updateArticle(id, articleRequest);
  }

  @DeleteMapping("{id}")
  public void deleteArticle(@PathVariable("id") Integer id) throws ArticleException {
    // TODO: handle exception

    articleService.deleteArticle(id);
  }


}
