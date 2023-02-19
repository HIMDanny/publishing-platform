package com.publishing.user.model;

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
    @Transient
    private List<EntityArticleResponseDto> articles = new ArrayList<>();
}
