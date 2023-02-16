package com.publishing.article;

public record ArticleRequest(String title,
                             String content,
                             String mainImagePath,
                             Integer userId,
                             Integer categoryId) {

}
