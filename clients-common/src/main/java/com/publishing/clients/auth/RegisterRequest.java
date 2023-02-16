package com.publishing.clients.auth;

import com.publishing.clients.user.Role;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest extends UserRequest{
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;
}