package com.publishing.article.controller;

import com.publishing.article.service.ArticleService;
import com.publishing.article.dto.ArticleRequestDto;
import com.publishing.util.FileUploadUtil;
import com.publishing.clients.article.dto.EntityArticleResponseDto;
import com.publishing.exception.ArticleException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
  public Integer saveArticle(@RequestBody ArticleRequestDto articleRequestDto,
                             @RequestParam("image")MultipartFile multipartFile){
    String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
    articleRequestDto.setMainImagePath(fileName);
    Integer articleId = articleService.saveArticle(articleRequestDto);
    String uploadDir = "article-images/" + articleId;
    FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
    return articleId;
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

  @GetMapping(params = {"field"})
  @ResponseStatus(HttpStatus.OK)
  private List<EntityArticleResponseDto> getArticlesWithSort(
                                    @RequestParam(value = "field") String field,
                                    @RequestParam(value = "direction", required = false) String direction){
    return articleService.findArticlesWithSorting(field, direction);
  }
}
