package com.publishing.service;

import com.publishing.clients.user.dto.UserAuthResponseDto;
import com.publishing.clients.user.dto.UserRequestDto;
import com.publishing.dto.AuthenticationResponse;
import com.publishing.clients.auth.dto.AuthenticationRequestDto;
import com.publishing.clients.user.UserClient;
import com.publishing.model.Role;
import com.publishing.model.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    UserAuthResponseDto userResponse = UserAuthResponseDto.builder()
            .email(request.getEmail())
            .firstName(request.getFirstName())
            .lastName(request.getLastName())
            .password(encodePassword)
            .role(Role.USER.name())
            .build();

    userClient.saveUser(UserRequestDto.builder().firstName(
            userResponse.getFirstName()).lastName(userResponse.getLastName())
            .email(userResponse.getEmail()).password(userResponse.getPassword())
            .build(), null);

    UserDetailsImpl userDetails = getUserDetails(userResponse);

    String jwtToken = jwtService.generateToken(userDetails);
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

    UserAuthResponseDto userFromDb = userClient.getByEmailToAuthenticate(request.getEmail());

    if(userFromDb.getId() == 0)
      throw new UsernameNotFoundException("User with email " + request.getEmail() + " is not found");

    UserDetailsImpl userDetails = getUserDetails(userFromDb);

    var jwtToken = jwtService.generateToken(userDetails);
    return AuthenticationResponse.builder()
        .token(jwtToken)
        .build();
  }

  private UserDetailsImpl getUserDetails(UserAuthResponseDto request) {
    return UserDetailsImpl.builder()
        .username(request.getEmail())
        .password(request.getPassword())
        .role(Role.valueOf(request.getRole().toUpperCase()))
        .build();
  }
}
