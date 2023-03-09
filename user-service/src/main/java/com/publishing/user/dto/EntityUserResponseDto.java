package com.publishing.user.dto;

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
public class EntityUserResponseDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String role;
    private ArticlePageResponseDto page;
}
