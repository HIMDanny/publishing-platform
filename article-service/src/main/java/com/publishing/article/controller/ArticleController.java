package com.publishing.article.controller;

import com.publishing.article.dto.ArticlePageResponseDto;
import com.publishing.article.service.ArticleService;
import com.publishing.article.dto.ArticleRequestDto;
import com.publishing.clients.article.dto.EntityArticleResponseDto;
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

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<EntityArticleResponseDto> getAllArticles(){
    return articleService.getAllArticles();
  }

  @GetMapping("{id}")
  @ResponseStatus(HttpStatus.OK)
  public EntityArticleResponseDto getArticleById(@PathVariable("id") Integer id) throws ArticleException {
    // TODO: handle exception
    return articleService.getArticle(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Integer saveArticle(@RequestBody ArticleRequestDto articleRequestDto){
    return articleService.saveArticle(articleRequestDto);
  }

  @PutMapping("{id}")
  @ResponseStatus(HttpStatus.OK)
  public EntityArticleResponseDto updateArticle(@PathVariable("id") Integer id, @RequestBody ArticleRequestDto articleRequestDto)
      throws ArticleException {
    // TODO: handle exception

    return articleService.updateArticle(id, articleRequestDto);
  }

  @DeleteMapping("{id}")
  @ResponseStatus(HttpStatus.OK)
  public void deleteArticle(@PathVariable("id") Integer id) throws ArticleException {
    // TODO: handle exception

    articleService.deleteArticle(id);
  }

  @GetMapping(params = "field")
  private List<EntityArticleResponseDto> getArticlesWithSort(@RequestParam("field") String field){
    return articleService.findArticlesWithSorting(field);
  }

  @GetMapping("pagination/{offset}/{pageSize}")
  public ArticlePageResponseDto getArticlesWithPagination(@PathVariable int offset, @PathVariable int pageSize){
    return articleService.findArticlesWithPagination(offset, pageSize);
  }

  @GetMapping(path = "pagination/{offset}/{pageSize}", params = "field")
  public ArticlePageResponseDto getArticlesWithPaginationAndSort(
                                                      @PathVariable int offset,
                                                      @PathVariable int pageSize,
                                                      @RequestParam("field") String field){
    return articleService.findArticlesWithPaginationAndSorting(offset, pageSize, field);
  }
}
