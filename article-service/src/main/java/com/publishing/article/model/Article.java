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
    private Integer numberOfViews = 0;
    @Column
    private Integer numberOfLikes = 0;
    @Transient
    private UserResponseDto author;
    @Transient
    private CategoryResponseDto category;

    public void constructImagePath() {
        if(getMainImagePath() != null){
            this.setMainImagePath("article-images/" + this.getId() + "/" + this.getMainImagePath());
        }
    }
}
