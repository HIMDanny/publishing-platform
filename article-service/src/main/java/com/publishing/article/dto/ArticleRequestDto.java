package com.publishing.article.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticleRequestDto{
    @NotNull(message = "The title should not be empty")
    @NotEmpty(message = "The title should not be empty")
    private String title;
    @NotNull(message = "The content should not be empty")
    @NotEmpty(message = "The content should not be empty")
    private String content;
    private Integer authorId;
    private Integer categoryId;


}
