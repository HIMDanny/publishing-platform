package com.publishing.clients.auth;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationRequest extends UserRequest{
    private String email;
    private String password;
}