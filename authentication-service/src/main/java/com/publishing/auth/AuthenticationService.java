package com.publishing.auth;

import com.publishing.clients.auth.RegisterRequest;
import com.publishing.clients.auth.AuthenticationRequest;
import com.publishing.clients.auth.UserRequest;
import com.publishing.clients.user.UserClient;
import com.publishing.config.JwtService;
import com.publishing.clients.user.Role;
import com.publishing.user_credentials.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

  private final UserClient userClient;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public AuthenticationResponse register(RegisterRequest request) {
    String encodePassword = passwordEncoder.encode(request.getPassword());
    RegisterRequest userRequest = RegisterRequest.builder()
        .firstName(request.getFirstName())
        .lastName(request.getLastName())
        .email(request.getEmail())
        .password(encodePassword)
        .build();

    userClient.saveUser(userRequest);
    UserDetailsImpl user = getUserDetails(request, encodePassword);

    var jwtToken = jwtService.generateToken(user);
    return AuthenticationResponse.builder()
        .token(jwtToken)
        .build();
  }

  public AuthenticationResponse authenticate(AuthenticationRequest request) {

    authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(
          request.getEmail(),
          request.getPassword()
      )
    );

    RegisterRequest userRequest = userClient.getByEmail(request.getEmail())
        .orElseThrow();

    var user = getUserDetails(userRequest, passwordEncoder.encode(request.getPassword()));

    var jwtToken = jwtService.generateToken(user);
    return AuthenticationResponse.builder()
        .token(jwtToken)
        .build();
  }

  private UserDetailsImpl getUserDetails(RegisterRequest request, String encodePassword) {
    return UserDetailsImpl.builder()
        .username(request.getEmail())
        .password(encodePassword)
        .role(Role.USER)
        .build();
  }
}
