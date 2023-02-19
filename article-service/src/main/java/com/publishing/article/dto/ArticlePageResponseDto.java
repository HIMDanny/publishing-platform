package com.publishing.article.dto;

import com.publishing.clients.article.dto.EntityArticleResponseDto;
import lombok.*;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticlePageResponseDto {
    private Long totalElements;
    private Integer totalPages;
    private List<EntityArticleResponseDto> articles;
    private Integer page;
    private Integer pageSize;
}
