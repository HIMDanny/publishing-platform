package com.publishing.auth;

import com.publishing.clients.user.dto.UserRequestDto;
import com.publishing.clients.auth.dto.AuthenticationRequestDto;
import com.publishing.clients.user.UserClient;
import com.publishing.config.JwtService;
import com.publishing.dto.AuthenticationResponse;
import com.publishing.user_credentials.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

  private final UserClient userClient;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public AuthenticationResponse register(UserRequestDto request) {
    String encodePassword = passwordEncoder.encode(request.getPassword());
    UserRequestDto userRequest = UserRequestDto.builder()
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

  public AuthenticationResponse authenticate(AuthenticationRequestDto request) {
    authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(
          request.getEmail(),
          request.getPassword()
      )
    );
    var userRequest = userClient.getByEmailToAuthenticate(request.getEmail())
        .orElseThrow();

    UserRequestDto userRequestDto = UserRequestDto.builder()
            .email(userRequest.getEmail())
            .password(userRequest.getPassword())
            .build();

    var user = getUserDetails(userRequestDto, passwordEncoder.encode(request.getPassword()));

    var jwtToken = jwtService.generateToken(user);
    return AuthenticationResponse.builder()
        .token(jwtToken)
        .build();
  }

  private UserDetailsImpl getUserDetails(UserRequestDto request, String encodePassword) {
    return UserDetailsImpl.builder()
        .username(request.getEmail())
        .password(encodePassword)
        .role("ROLE_USER")
        .build();
  }
}
