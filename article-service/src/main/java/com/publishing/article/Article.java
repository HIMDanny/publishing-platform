package com.publishing.article;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Article{
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
//private User author;
//private Category category;

@Column
private Integer numberOfViews;
@Column
private Integer numberOfLikes;
    }
