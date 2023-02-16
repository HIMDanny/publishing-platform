package com.publishing.clients.auth;

import com.publishing.clients.user.Role;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest extends UserRequest{
//    @JsonProperty("firstName")
    private String firstName;
//    @JsonProperty("lastName")
    private String lastName;
//    @JsonProperty("email")
    private String email;
//    @JsonProperty("password")
    private String password;
//    @JsonProperty("role")
    private String role;

}