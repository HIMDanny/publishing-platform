package com.publishing.category.dto;

import com.publishing.clients.article.dto.ArticlePageResponseDto;
import com.publishing.clients.article.dto.EntityArticleResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EntityCategoryResponseDto {
    private Integer id;
    private String name;
//    private List<EntityArticleResponseDto> articles = new ArrayList<>();
    private ArticlePageResponseDto page;
}
