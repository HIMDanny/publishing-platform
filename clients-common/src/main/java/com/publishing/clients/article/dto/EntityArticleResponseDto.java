package com.publishing.clients.article.dto;

import com.publishing.clients.category.dto.CategoryResponseDto;
import com.publishing.clients.user.dto.UserResponseDto;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EntityArticleResponseDto {
    private Integer id;
    private String title;
    private String content;
    private String mainImagePath;
    private String imagesDir;
    private LocalDateTime publishingDate;
    private Integer minutesToRead;
    private Integer numberOfViews = 0;
    private Integer numberOfLikes = 0;
    private UserResponseDto author;
    private CategoryResponseDto category;
}
