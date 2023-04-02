package com.publishing.article.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "article_tag")
public class Tag {
    @SequenceGenerator(
            name = "tag_sequence",
            sequenceName = "tag_sequence"
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "tag_sequence"
    )
    private Integer id;
    private Integer articleId;
    private String tag;
}
