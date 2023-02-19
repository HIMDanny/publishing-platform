package com.publishing.controller;

import com.publishing.clients.user.dto.UserRequestDto;
import com.publishing.dto.AuthenticationResponse;
import com.publishing.service.AuthenticationService;
import com.publishing.clients.auth.dto.AuthenticationRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService service;

  @PostMapping("register")
  @ResponseStatus(HttpStatus.CREATED)
  public AuthenticationResponse register(@RequestBody UserRequestDto request){
    return service.register(request);
  }

  @PostMapping("authenticate")
  @ResponseStatus(HttpStatus.OK)
  public AuthenticationResponse authenticate(@RequestBody AuthenticationRequestDto request){
    return service.authenticate(request);
  }

}
