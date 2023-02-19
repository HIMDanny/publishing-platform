package com.publishing.config;

import com.publishing.clients.user.dto.UserAuthResponseDto;
import com.publishing.model.Role;
import com.publishing.clients.user.UserClient;
import com.publishing.model.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class ServiceConfig {

  private final UserClient userClient;

  @Bean
  public AuthenticationProvider authenticationProvider(){
    DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
    authenticationProvider.setUserDetailsService(userDetailsService());
    authenticationProvider.setPasswordEncoder(passwordEncoder());
    return authenticationProvider;
  }

  @Bean
  public UserDetailsService userDetailsService() {

    return username -> {
      UserAuthResponseDto userAuthResponseDto = userClient.getByEmailToAuthenticate(username);

      return UserDetailsImpl.builder()
          .username(userAuthResponseDto.getEmail())
          .password(userAuthResponseDto.getPassword())
          .role(Role.valueOf(userAuthResponseDto.getRole().toUpperCase()))
          .build();
    };
  }

  @Bean
  public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
    return config.getAuthenticationManager();
  }

}
