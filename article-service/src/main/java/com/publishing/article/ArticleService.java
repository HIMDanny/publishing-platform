package com.publishing.article;

import com.publishing.clients.article.Article;
import com.publishing.clients.user.UserClient;
import com.publishing.exception.ArticleException;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleService {

  private final ArticleRepository articleRepository;
  private final UserClient userClient;

  public Article getArticle(Integer id) throws ArticleException{
    // TODO: handle exception
    Article article = articleRepository.findById(id)
            .orElseThrow(() -> new ArticleException(String.format("Article with id %d cannot be found", id)));
    return article;
  }

  public List<Article> getAllArticles(){
    return articleRepository.findAll();
  }

  public Article saveArticle(ArticleRequest articleRequest){
    // TODO: calculate minutes to read
    int minutesToRead = 0;

    Article article = Article.builder()
            .title(articleRequest.title())
            .content(articleRequest.content())
            .mainImagePath(articleRequest.mainImagePath())
            .minutesToRead(minutesToRead)
            .publishingDate(LocalDateTime.now())
            .authorId(articleRequest.userId())
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
