package com.publishing.clients.article;

import com.publishing.clients.category.Category;
import com.publishing.clients.user.User;
import jakarta.persistence.*;
import lombok.*;
import net.minidev.json.annotate.JsonIgnore;

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
    @JsonIgnore
    private Integer authorId;
    @Column
    @JsonIgnore
    private Integer categoryId;
    @Column
    private Integer numberOfViews;
    @Column
    private Integer numberOfLikes;

    @Transient
    private User user;
    @Transient
    private Category category;
}
