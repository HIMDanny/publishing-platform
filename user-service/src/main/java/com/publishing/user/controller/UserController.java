package com.publishing.user.controller;

import com.publishing.clients.PaginationParameters;
import com.publishing.clients.user.dto.UserRequestDto;
import com.publishing.exception.CustomUserException;
import com.publishing.exception.EmailNotUniqueException;
import com.publishing.file.service.FileStorageService;
import com.publishing.user.dto.EntityUserResponseDto;
import com.publishing.user.dto.UserPageResponseDto;
import com.publishing.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

  private final FileStorageService fileStorageService;

  private final UserService userService;

  @GetMapping()
  @ResponseStatus(HttpStatus.OK)
  public List<EntityUserResponseDto> getAllUsers(){
    return userService.getAllUsers();
  }

  @GetMapping("{id}")
  @ResponseStatus(HttpStatus.OK)
  public EntityUserResponseDto getUser(@PathVariable("id") Integer id) throws CustomUserException {
    return userService.getUserById(id, PaginationParameters.builder().field("numberOfViews").build());
  }

  @GetMapping(params = "email")
  @ResponseStatus(HttpStatus.OK)
  public EntityUserResponseDto getByEmail(@RequestParam("email") String email) throws CustomUserException {
    return userService.getUserByEmail(email);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Integer saveUser(@Valid UserRequestDto user,
                          @RequestParam(value = "image", required = false)MultipartFile image) throws EmailNotUniqueException {
    Integer userId = userService.saveUser(user, image.getOriginalFilename());
    String fileName = fileStorageService.storeFile(userId, image);
    return userId;
  }

  @PutMapping("{id}")
  @ResponseStatus(HttpStatus.OK)
  public EntityUserResponseDto updateUser(@PathVariable("id") Integer id, @RequestParam(required = false) UserRequestDto user,
                                          @RequestParam(value = "image", required = false)MultipartFile image) throws CustomUserException, EmailNotUniqueException {
    EntityUserResponseDto entityUserResponseDto = userService.updateUser(id, user, image);

    if(image != null) {
      String fileName = fileStorageService.storeFile(id, image);
    }

    return entityUserResponseDto;
  }

  @DeleteMapping("{id}")
  @ResponseStatus(HttpStatus.OK)
  public boolean deleteUser(@PathVariable("id") Integer id) throws CustomUserException {
    return userService.deleteUser(id);
  }

  @GetMapping(params = {"field", "direction"})
  @ResponseStatus(HttpStatus.OK)
  public List<EntityUserResponseDto> findUsersWithSort(
                                @RequestParam(value = "field") String field,
                                @RequestParam(value = "direction", required = false) String direction){
    return userService.findUsersWithSort(field, direction);
  }

  @GetMapping("{id}/articles")
  @ResponseStatus(HttpStatus.OK)
  public EntityUserResponseDto getUserArticles(@PathVariable("id") Integer id,
                                                       PaginationParameters paginationParameters) throws CustomUserException {
    return userService.getUserById(id, paginationParameters);
  }

  @GetMapping("{id}/articles/likes")
  @ResponseStatus(HttpStatus.OK)
  public EntityUserResponseDto getUserArticlesLike(@PathVariable("id") Integer id,
                                                   PaginationParameters paginationParameters) throws CustomUserException {
    return userService.getUserWithLikedArticles(id, paginationParameters);
  }

  @GetMapping("{id}/articles/bookmarks")
  @ResponseStatus(HttpStatus.OK)
  public EntityUserResponseDto getUserArticlesBookmarks(@PathVariable("id") Integer id,
                                                   PaginationParameters paginationParameters) throws CustomUserException {
    return userService.getUserWithBookmarkedArticles(id, paginationParameters);
  }

}
