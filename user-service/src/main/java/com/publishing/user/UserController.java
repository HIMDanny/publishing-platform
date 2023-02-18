package com.publishing.user;

import com.publishing.clients.auth.RegisterRequest;
import com.publishing.clients.user.User;
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
  public User getUser(@PathVariable("id") Integer id){
    return userService.getUserById(id);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public User getByEmail(@RequestParam("email") String email){
    return userService.getUserByEmail(email);
  }

  @GetMapping("all")
  @ResponseStatus(HttpStatus.OK)
  public List<User> getAllUsers(){
    return userService.getAllUsers();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public boolean saveUser(@RequestBody RegisterRequest user){
    return userService.saveUser(user);
  }

  @PutMapping("{id}")
  @ResponseStatus(HttpStatus.OK)
  public User updateUser(@PathVariable("id") Integer id, @RequestBody User user){
    return userService.updateUser(id, user);
  }

  @DeleteMapping("{id}")
  @ResponseStatus(HttpStatus.OK)
  public boolean deleteUser(@PathVariable("id") Integer id){
    return userService.deleteUser(id);
  }
}
