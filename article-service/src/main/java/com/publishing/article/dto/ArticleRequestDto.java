package com.publishing.article.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticleRequestDto{
    private String title;
    private String content;
    private Integer authorId;
    private Integer categoryId;

}
