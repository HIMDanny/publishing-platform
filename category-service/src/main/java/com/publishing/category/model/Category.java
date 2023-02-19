package com.publishing.category.model;

import com.publishing.clients.article.dto.EntityArticleResponseDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Category {
    @SequenceGenerator(
            name = "category_sequence",
            sequenceName = "category_sequence"
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "category_sequence"
    )
    private Integer id;
    private String name;
    @Transient
    private List<EntityArticleResponseDto> articles = new ArrayList<>();
}
