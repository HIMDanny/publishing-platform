package com.publishing.article.service;

import com.publishing.article.dto.ArticlePageResponseDto;
import com.publishing.article.repo.ArticleRepository;
import com.publishing.article.dto.ArticleRequestDto;
import com.publishing.article.model.Article;
import com.publishing.clients.article.dto.EntityArticleResponseDto;
import com.publishing.clients.category.CategoryClient;
import com.publishing.clients.category.dto.CategoryResponseDto;
import com.publishing.clients.user.UserClient;
import com.publishing.clients.user.dto.UserResponseDto;
import com.publishing.exception.ArticleException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleService {

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

    return mapToArticleDTO(article);
  }

  public List<EntityArticleResponseDto> getAllArticles(){
    List<Article> articles = articleRepository.findAll();

    return getListOfArticleDTOS(articles);
  }

  public List<EntityArticleResponseDto> findArticlesWithSorting(String field){
    List<Article> articles = articleRepository.findAll(Sort.by(Sort.Direction.ASC, field));

    return getListOfArticleDTOS(articles);
  }

  public ArticlePageResponseDto findArticlesWithPagination(int offset, int pageSize){
    Page<Article> pageOfArticles = articleRepository.findAll(PageRequest.of(offset - 1, pageSize));

    List<Article> articles = pageOfArticles.stream().collect(Collectors.toList());

    List<EntityArticleResponseDto> articleDtos = getListOfArticleDTOS(articles);

    return ArticlePageResponseDto.builder()
            .totalElements(pageOfArticles.getTotalElements())
            .totalPages(pageOfArticles.getTotalPages())
            .page(offset)
            .pageSize(pageSize)
            .articles(articleDtos)
            .build();
  }

  public ArticlePageResponseDto findArticlesWithPaginationAndSorting(int offset, int pageSize, String field){
    Page<Article> pageOfArticles = articleRepository.findAll(PageRequest.of(offset - 1, pageSize).withSort(Sort.by(field)));

    List<Article> articles = pageOfArticles.stream().collect(Collectors.toList());

    List<EntityArticleResponseDto> articleDtos = getListOfArticleDTOS(articles);

    return ArticlePageResponseDto.builder()
            .totalElements(pageOfArticles.getTotalElements())
            .totalPages(pageOfArticles.getTotalPages())
            .page(offset)
            .pageSize(pageSize)
            .articles(articleDtos)
            .build();
  }

  public Integer saveArticle(ArticleRequestDto articleRequestDto){
    long numberOfWords = articleRequestDto.getContent().split(" ").length;
    int minutesToRead = (int) Math.ceil(numberOfWords / 200.0);

    Article article = Article.builder()
            .title(articleRequestDto.getTitle())
            .content(articleRequestDto.getContent())
            .mainImagePath(articleRequestDto.getMainImagePath())
            .minutesToRead(minutesToRead)
            .publishingDate(LocalDateTime.now())
            .authorId(articleRequestDto.getAuthorId())
            .categoryId(articleRequestDto.getCategoryId())
        .build();

    Article savedArticle = articleRepository.save(article);
    return savedArticle.getId();
  }

  public EntityArticleResponseDto updateArticle(Integer id, ArticleRequestDto articleRequestDto) throws ArticleException{
    // TODO: handle exception

    Article foundArticleInDb = articleRepository.findById(id)
        .orElseThrow(() -> new ArticleException(String.format("Article with id %d cannot be found", id)));

    foundArticleInDb.setTitle(articleRequestDto.getTitle());
    foundArticleInDb.setContent(articleRequestDto.getContent());
    foundArticleInDb.setMainImagePath(articleRequestDto.getMainImagePath());
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

  public List<EntityArticleResponseDto> getArticlesByCategory(Integer categoryId) {
    List<Article> foundArticles = articleRepository.findAllByCategoryId(categoryId);

    Map<Integer, UserResponseDto> authors = getMapWithUserIds(foundArticles);

    return foundArticles.stream()
            .peek(article -> article.setAuthor(authors.get(article.getAuthorId())))
            .map(this::mapToArticleDTO)
            .collect(Collectors.toList());
  }

  public List<EntityArticleResponseDto> getArticlesByAuthor(Integer userId) {
    List<Article> foundArticle = articleRepository.findAllByAuthorId(userId);

    Map<Integer, CategoryResponseDto> categories = getMapWithCategoryIds(foundArticle);

    return foundArticle.stream()
            .peek(article -> article.setCategory(categories.get(article.getCategoryId())))
            .map(this::mapToArticleDTO)
            .collect(Collectors.toList());
  }

  private EntityArticleResponseDto mapToArticleDTO(Article article) {
    return EntityArticleResponseDto.builder()
            .id(article.getId())
            .title(article.getTitle())
            .content(article.getContent())
            .mainImagePath(article.getMainImagePath())
            .publishingDate(article.getPublishingDate())
            .minutesToRead(article.getMinutesToRead())
            .numberOfViews(article.getNumberOfViews())
            .numberOfLikes(article.getNumberOfLikes())
            .category(article.getCategory())
            .author(article.getAuthor())
            .build();
  }

  private List<EntityArticleResponseDto> getListOfArticleDTOS(List<Article> articles) {

    Map<Integer, CategoryResponseDto> categories = getMapWithCategoryIds(articles);

    Map<Integer, UserResponseDto> authors = getMapWithUserIds(articles);

    return articles.stream()
            .peek(article -> article.setCategory(categories.get(article.getCategoryId())))
            .peek(article -> article.setAuthor(authors.get(article.getAuthorId())))
            .map(this::mapToArticleDTO)
            .collect(Collectors.toList());
  }

  private Map<Integer, UserResponseDto> getMapWithUserIds(List<Article> articles) {
    List<Integer> authorIds = new ArrayList<>();
    return articles.stream()
            .filter(article -> !authorIds.contains(article.getAuthorId()))
            .peek(article -> authorIds.add(article.getAuthorId()))
            .map(article -> userClient.getUserResponse(article.getAuthorId()))
            .collect(Collectors.toMap(UserResponseDto::getId, Function.identity()));
  }

  private Map<Integer, CategoryResponseDto> getMapWithCategoryIds(List<Article> articles) {
    List<Integer> categoriesIds = new ArrayList<>();
    return articles.stream()
            .filter(article -> !categoriesIds.contains(article.getCategoryId()))
            .peek(article -> categoriesIds.add(article.getCategoryId()))
            .map(article -> categoryClient.getCategoryResponse(article.getCategoryId()))
            .map(categoryResponseDto -> CategoryResponseDto.builder().id(categoryResponseDto.getId()).name(categoryResponseDto.getName()).build())
            .collect(Collectors.toMap(CategoryResponseDto::getId, Function.identity()));
  }
}
