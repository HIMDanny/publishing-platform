package com.publishing.auth;

import com.publishing.clients.auth.RegisterRequest;
import com.publishing.clients.auth.AuthenticationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService service;

  @PostMapping("register")
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<AuthenticationResponse> register(
      @RequestBody RegisterRequest request
  ){
    return ResponseEntity.ok(service.register(request));
  }

  @PostMapping("authenticate")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<AuthenticationResponse> authenticate(
    @RequestBody AuthenticationRequest request
  ){
    return ResponseEntity.ok(service.authenticate(request));
  }

}
