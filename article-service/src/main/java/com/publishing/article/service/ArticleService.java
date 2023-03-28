package com.publishing.article.service;

import com.publishing.article.repo.ArticleRepository;
import com.publishing.article.dto.ArticleRequestDto;
import com.publishing.article.model.Article;
import com.publishing.clients.PaginationParameters;
import com.publishing.clients.article.dto.ArticlePageResponseDto;
import com.publishing.clients.article.dto.EntityArticleResponseDto;
import com.publishing.clients.category.CategoryClient;
import com.publishing.clients.category.dto.CategoryResponseDto;
import com.publishing.clients.user.UserClient;
import com.publishing.clients.user.dto.UserResponseDto;
import com.publishing.exception.ArticleException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ArticleService extends ArticleCommonService{

  private final ArticleRepository articleRepository;
  private final UserClient userClient;
  private final CategoryClient categoryClient;

  public EntityArticleResponseDto getArticle(Integer id) throws ArticleException{
    // TODO: handle exception
    Article article = articleRepository.findById(id)
            .orElseThrow(() -> new ArticleException(String.format("Article with id %d cannot be found", id)));

    CategoryResponseDto categoryResponseDto = categoryClient.getCategoryResponse(article.getCategoryId());
    UserResponseDto userResponseDto = userClient.getUserResponse(article.getAuthorId());
    article.setCategory(categoryResponseDto);
    article.setAuthor(userResponseDto);

    articleRepository.increaseViewsById(id);

    return mapToArticleDTO(article);
  }

  public List<EntityArticleResponseDto> getAllArticles(){
    List<Article> articles = articleRepository.findAll();

    for(Article article : articles){
      if(article.getMainImagePath() != null){
        article.setMainImagePath("article-images/" + article.getId() + "/" + article.getMainImagePath());
      }
    }

    return getListOfArticleDTOS(articles);
  }

  public List<EntityArticleResponseDto> findArticlesWithSorting(String field, String dirVal){
    Sort.Direction direction = (dirVal != null && dirVal.equalsIgnoreCase("desc"))
            ? Sort.Direction.DESC : Sort.Direction.ASC;

    List<Article> articles = articleRepository.findAll(Sort.by(direction, field));

    return getListOfArticleDTOS(articles);
  }

  public Integer saveArticle(ArticleRequestDto articleRequestDto, String fileName){
    long numberOfWords = articleRequestDto.getContent().split(" ").length;
    int minutesToRead = (int) Math.ceil(numberOfWords / 200.0);

    Article article = Article.builder()
            .title(articleRequestDto.getTitle())
            .content(articleRequestDto.getContent())
            .mainImagePath(fileName)
            .minutesToRead(minutesToRead)
            .publishingDate(LocalDateTime.now())
            .authorId(articleRequestDto.getAuthorId())
            .categoryId(articleRequestDto.getCategoryId())
        .build();

    Article savedArticle = articleRepository.save(article);
    return savedArticle.getId();
  }

  public EntityArticleResponseDto updateArticle(Integer id, ArticleRequestDto articleRequestDto, MultipartFile mainImage) throws ArticleException{
    // TODO: handle exception

    Article foundArticleInDb = articleRepository.findById(id)
        .orElseThrow(() -> new ArticleException(String.format("Article with id %d cannot be found", id)));

    foundArticleInDb.setTitle(articleRequestDto.getTitle());
    foundArticleInDb.setContent(articleRequestDto.getContent());
    if(mainImage != null)
      foundArticleInDb.setMainImagePath(mainImage.getOriginalFilename());
    foundArticleInDb.setAuthorId(articleRequestDto.getAuthorId());
    foundArticleInDb.setCategoryId(articleRequestDto.getCategoryId());

    Article updatedArticle = articleRepository.save(foundArticleInDb);

    CategoryResponseDto categoryResponseDto = categoryClient.getCategoryResponse(updatedArticle.getCategoryId());
    UserResponseDto userResponseDto = userClient.getUserResponse(updatedArticle.getAuthorId());
    updatedArticle.setCategory(categoryResponseDto);
    updatedArticle.setAuthor(userResponseDto);

    return mapToArticleDTO(updatedArticle);
  }

  public void deleteArticle(Integer id) throws ArticleException{
    // TODO: handle exception

    Article foundArticleInDb = articleRepository.findById(id)
        .orElseThrow(() -> new ArticleException(String.format("Article with id %d cannot be found", id)));

    articleRepository.delete(foundArticleInDb);
  }

  public ArticlePageResponseDto getArticlesPageByCategory(Integer categoryId, PaginationParameters params) {
    Sort.Direction direction = Sort.Direction.valueOf(params.getDirection());

    Page<Article> pageOfArticles = articleRepository.findAllByCategoryId(
            categoryId,
            PageRequest.of(params.getPage() - 1, params.getPageSize()).withSort(Sort.by(direction, params.getField())));

    List<Article> articles = pageOfArticles.stream().collect(Collectors.toList());

    List<EntityArticleResponseDto> articleDtos = getListOfArticleDTOS(articles);

    return ArticlePageResponseDto.builder()
            .totalElements(pageOfArticles.getTotalElements())
            .totalPages(pageOfArticles.getTotalPages())
            .page(params.getPage())
            .pageSize(params.getPageSize())
            .articles(articleDtos)
            .build();
  }

  public ArticlePageResponseDto getArticlesPageByAuthor(Integer userId, PaginationParameters params) {
    Sort.Direction direction = Sort.Direction.valueOf(params.getDirection());

    Page<Article> pageOfArticles = articleRepository.findAllByAuthorId(
            userId,
            PageRequest.of(params.getPage() - 1, params.getPageSize()).withSort(Sort.by(direction, params.getField())));

    List<Article> articles = pageOfArticles.stream().collect(Collectors.toList());

    List<EntityArticleResponseDto> articleDtos = getListOfArticleDTOS(articles);

    return ArticlePageResponseDto.builder()
            .totalElements(pageOfArticles.getTotalElements())
            .totalPages(pageOfArticles.getTotalPages())
            .page(params.getPage())
            .pageSize(params.getPageSize())
            .articles(articleDtos)
            .build();
  }
}
