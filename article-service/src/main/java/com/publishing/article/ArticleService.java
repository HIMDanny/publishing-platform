package com.publishing.article;

import com.publishing.clients.article.Article;
import com.publishing.clients.auth.RegisterRequest;
import com.publishing.clients.category.Category;
import com.publishing.clients.category.CategoryClient;
import com.publishing.clients.category.CategoryResponse;
import com.publishing.clients.user.User;
import com.publishing.clients.user.UserClient;
import com.publishing.exception.ArticleException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleService {

  private final ArticleRepository articleRepository;
  private final UserClient userClient;
  private final CategoryClient categoryClient;

  public Article getArticle(Integer id) throws ArticleException{
    // TODO: handle exception
    Article article = articleRepository.findById(id)
            .orElseThrow(() -> new ArticleException(String.format("Article with id %d cannot be found", id)));

    CategoryResponse categoryResponse = categoryClient.getCategoryResponse(article.getCategoryId());
    article.setCategory(Category.builder()
            .id(categoryResponse.getId())
            .name(categoryResponse.getName())
            .build());

    article.setAuthor(userClient.getUser(article.getAuthorId()));

    return article;
  }

  public List<Article> getAllArticles(){
    List<Article> articles = articleRepository.findAll();

    List<Integer> categoriesIds = new ArrayList<>();
    Map<Integer, Category> categories = articles.stream()
            .filter(article -> !categoriesIds.contains(article.getCategoryId()))
            .peek(article -> categoriesIds.add(article.getCategoryId()))
            .map(article -> categoryClient.getCategoryResponse(article.getCategoryId()))
            .map(categoryResponse -> Category.builder().id(categoryResponse.getId()).name(categoryResponse.getName()).build())
            .collect(Collectors.toMap(Category::getId, Function.identity()));

    List<Integer> authorIds = new ArrayList<>();
    Map<Integer, User> authors = articles.stream()
            .filter(article -> !authorIds.contains(article.getAuthorId()))
            .peek(article -> authorIds.add(article.getAuthorId()))
            .map(article -> userClient.getUser(article.getAuthorId()))
            .collect(Collectors.toMap(User::getId, Function.identity()));

    return articles.stream()
            .peek(article -> article.setCategory(categories.get(article.getCategoryId())))
            .peek(article -> article.setAuthor(authors.get(article.getAuthorId())))
            .collect(Collectors.toList());
  }

  public Article saveArticle(ArticleRequest articleRequest){
    long numberOfWords = articleRequest.content().split(" ").length;
    int minutesToRead = (int) Math.ceil(numberOfWords / 200.0);

    Article article = Article.builder()
            .title(articleRequest.title())
            .content(articleRequest.content())
            .mainImagePath(articleRequest.mainImagePath())
            .minutesToRead(minutesToRead)
            .publishingDate(LocalDateTime.now())
            .authorId(articleRequest.userId())
            .categoryId(articleRequest.categoryId())
        .build();

    return articleRepository.save(article);
  }

  public Article updateArticle(Integer id, ArticleRequest articleRequest) throws ArticleException{
    // TODO: handle exception

    Article foundArticleInDb = articleRepository.findById(id)
        .orElseThrow(() -> new ArticleException(String.format("Article with id %d cannot be found", id)));

    foundArticleInDb.setTitle(articleRequest.title());
    foundArticleInDb.setContent(articleRequest.content());
    foundArticleInDb.setMainImagePath(articleRequest.mainImagePath());

    return articleRepository.save(foundArticleInDb);
  }

  public void deleteArticle(Integer id) throws ArticleException{
    // TODO: handle exception

    Article foundArticleInDb = articleRepository.findById(id)
        .orElseThrow(() -> new ArticleException(String.format("Article with id %d cannot be found", id)));

    articleRepository.delete(foundArticleInDb);
  }

  public List<Article> getArticlesByCategory(Integer categoryId) {
    return articleRepository.findAllByCategoryId(categoryId);
  }
}
