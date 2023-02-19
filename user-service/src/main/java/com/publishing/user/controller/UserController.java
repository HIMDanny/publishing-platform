package com.publishing.user.controller;

import com.publishing.clients.user.dto.UserRequestDto;
import com.publishing.user.dto.EntityUserResponseDto;
import com.publishing.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @GetMapping("{id}")
  @ResponseStatus(HttpStatus.OK)
  public EntityUserResponseDto getUser(@PathVariable("id") Integer id){
    return userService.getUserById(id);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public EntityUserResponseDto getByEmail(@RequestParam("email") String email){
    return userService.getUserByEmail(email);
  }

  @GetMapping("all")
  @ResponseStatus(HttpStatus.OK)
  public List<EntityUserResponseDto> getAllUsers(){
    return userService.getAllUsers();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Integer saveUser(@RequestBody UserRequestDto user){
    return userService.saveUser(user);
  }

  @PutMapping("{id}")
  @ResponseStatus(HttpStatus.OK)
  public EntityUserResponseDto updateUser(@PathVariable("id") Integer id, @RequestBody UserRequestDto user){
    return userService.updateUser(id, user);
  }

  @DeleteMapping("{id}")
  @ResponseStatus(HttpStatus.OK)
  public boolean deleteUser(@PathVariable("id") Integer id){
    return userService.deleteUser(id);
  }
}
