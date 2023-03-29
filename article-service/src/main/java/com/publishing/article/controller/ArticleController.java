package com.publishing.article.controller;

import com.publishing.article.service.ArticleService;
import com.publishing.article.dto.ArticleRequestDto;
import com.publishing.clients.article.dto.EntityArticleResponseDto;
import com.publishing.exception.ArticleException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.publishing.file.service.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/v1/articles")
@RequiredArgsConstructor
public class ArticleController {

  private final ArticleService articleService;
  private final FileStorageService fileStorageService;

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
  public Integer saveArticle(ArticleRequestDto articleRequestDto,
                             @RequestParam("mainImage")MultipartFile mainImage,
                             @RequestParam(value = "images", required = false)MultipartFile[] images){

    Integer articleId = articleService.saveArticle(articleRequestDto, mainImage.getOriginalFilename());
    String fileName = fileStorageService.storeFile(articleId, mainImage);

    if(images != null) {
      Arrays.stream(images)
              .forEach(image -> fileStorageService.storeFile(articleId, image));
    }

    return articleId;
  }

  @PutMapping("{id}")
  @ResponseStatus(HttpStatus.OK)
  public EntityArticleResponseDto updateArticle(@PathVariable("id") Integer id, ArticleRequestDto articleRequestDto,
                                                @RequestParam(value = "mainImage", required = false)MultipartFile file,
                                                @RequestParam(value = "images", required = false)MultipartFile[] images)
      throws ArticleException {
    // TODO: handle exception

    if(file != null){
      String fileName = fileStorageService.storeFile(id, file);
    }

    if(images != null) {
      Arrays.stream(images)
              .forEach(image -> fileStorageService.storeFile(id, image));
    }

    return articleService.updateArticle(id, articleRequestDto, file);
  }

  @DeleteMapping("{id}")
  @ResponseStatus(HttpStatus.OK)
  public void deleteArticle(@PathVariable("id") Integer id) throws ArticleException {
    // TODO: handle exception

    articleService.deleteArticle(id);
  }

  @GetMapping(params = {"field"})
  @ResponseStatus(HttpStatus.OK)
  public List<EntityArticleResponseDto> getArticlesWithSort(
                                    @RequestParam(value = "field") String field,
                                    @RequestParam(value = "direction", required = false) String direction){
    return articleService.findArticlesWithSorting(field, direction);
  }

  @PostMapping("{articleId}/like")
  @ResponseStatus(HttpStatus.OK)
  public void likeArticle(@PathVariable("articleId") Integer articleId,
                          @RequestParam("userId") Integer userId){
    articleService.likeArticle(articleId, userId);
  }

  @PostMapping("{articleId}/bookmark")
  @ResponseStatus(HttpStatus.OK)
  public void bookmarkArticle(@PathVariable("articleId") Integer articleId,
                          @RequestParam("userId") Integer userId){
    articleService.bookmarkArticle(articleId, userId);
  }

  @DeleteMapping("{articleId}/like")
  @ResponseStatus(HttpStatus.OK)
  public void deleteLikeArticle(@PathVariable("articleId") Integer articleId,
                          @RequestParam("userId") Integer userId){
    articleService.deleteLikeArticle(articleId, userId);
  }

  @DeleteMapping("{articleId}/bookmark")
  @ResponseStatus(HttpStatus.OK)
  public void deleteBookmarkArticle(@PathVariable("articleId") Integer articleId,
                              @RequestParam("userId") Integer userId) {
    articleService.deleteBookmarkArticle(articleId, userId);
  }
}
