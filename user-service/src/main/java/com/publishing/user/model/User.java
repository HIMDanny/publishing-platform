package com.publishing.user.model;

import com.publishing.clients.article.dto.ArticlePageResponseDto;
import com.publishing.clients.article.dto.EntityArticleResponseDto;
import com.publishing.util.FileStorageProperties;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "app_user")
public class User {

    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence"
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String role;
    private String image;
    @Transient
//    private List<EntityArticleResponseDto> articles = new ArrayList<>();
    private ArticlePageResponseDto page;

}
