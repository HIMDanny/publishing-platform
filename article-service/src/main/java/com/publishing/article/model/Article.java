package com.publishing.article.model;

import com.publishing.clients.category.dto.CategoryResponseDto;
import com.publishing.clients.user.dto.UserResponseDto;
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
    @Column(length = 500)
    private String title;
    @Column(columnDefinition = "TEXT")
    private String content;
    @Column(name = "main_image_path")
    private String mainImagePath;
    @Column(name = "publishing_date")
    private LocalDateTime publishingDate;
    @Column(name = "minutes_to_read")
    private Integer minutesToRead;
    @Column
    @JsonIgnore
    private Integer authorId;
    @Column
    @JsonIgnore
    private Integer categoryId;
    @Column(name = "number_of_views", columnDefinition = "integer not null default 0")
    private Integer numberOfViews = 0;
    @Column(name = "number_of_likes", columnDefinition = "integer not null default 0")
    private Integer numberOfLikes = 0;
    @Transient
    private UserResponseDto author;
    @Transient
    private CategoryResponseDto category;
    @Transient
    private String imagesDir;

    public String getImagesDir(){
        return "images/article-images/" + getId();
    }
}
