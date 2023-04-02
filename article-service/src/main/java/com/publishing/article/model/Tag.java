package com.publishing.article.model;

import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "article_tag")
public class Tag {
    private Integer id;
    private Integer articleId;
    private String tag;
}
