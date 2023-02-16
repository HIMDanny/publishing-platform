package com.publishing.user;

import com.publishing.clients.auth.RegisterRequest;
import com.publishing.clients.user.User;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @GetMapping("{id}")
  public User getUser(@PathVariable("id") Integer id){
    return userService.getById(id);
  }

  @GetMapping
  public User getByEmail(@RequestParam("email") String email){
    return userService.getByEmail(email);
  }

  @PostMapping
  public boolean saveUser(@RequestBody RegisterRequest user){
    return userService.saveUser(user);
  }

  @PutMapping("{id}")
  public User updateUser(@PathVariable("id") Integer id, @RequestBody User user){
    return userService.updateUser(id, user);
  }

  @DeleteMapping("{id}")
  public boolean deleteUser(@PathVariable("id") Integer id){
    return userService.deleteUser(id);
  }
}