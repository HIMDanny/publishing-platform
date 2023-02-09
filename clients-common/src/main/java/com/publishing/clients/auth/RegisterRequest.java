package com.publishing.clients.auth;

import com.publishing.clients.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class RegisterRequest extends UserRequest{
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;
}