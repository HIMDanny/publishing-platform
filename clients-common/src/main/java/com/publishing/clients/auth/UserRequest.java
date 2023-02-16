package com.publishing.clients.auth;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class UserRequest {
    private String username;
}