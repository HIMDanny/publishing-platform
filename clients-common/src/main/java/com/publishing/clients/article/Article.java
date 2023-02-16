package com.publishing.clients.article;

import com.publishing.clients.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Article {
    @SequenceGenerator(
            name = "article_sequence",
            sequenceName = "article_sequence"
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "article_sequence"
    )
    private Integer id;
    @Column
    private String title;
    @Column
    private String content;
    @Column
    private String mainImagePath;
    @Column
    private LocalDateTime publishingDate;
    @Column
    private Integer minutesToRead;
    @Column
    private Integer authorId;
//    @Column
//    private Category category;

    @Column
    private Integer numberOfViews;
    @Column
    private Integer numberOfLikes;
}
