package com.publishing.user.service;

import com.publishing.clients.article.ArticleClient;
import com.publishing.clients.user.dto.UserAuthResponseDto;
import com.publishing.clients.user.dto.UserRequestDto;
import com.publishing.clients.user.dto.UserResponseDto;
import com.publishing.exception.CustomUserException;
import com.publishing.user.dto.EntityUserResponseDto;
import com.publishing.user.dto.UserPageResponseDto;
import com.publishing.user.model.User;
import com.publishing.user.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService extends UserServiceCommon{

  private final UserRepository userRepository;
  private final ArticleClient articleClient;

  public EntityUserResponseDto getUserById(Integer id) {
    // TODO: add custom exception
    User user = userRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("User is not found"));

    user.setArticles(articleClient.getArticleResponsesByUser(id));
    return mapToDto(user);
  }

  public EntityUserResponseDto getUserByEmail(String email) {
    User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new IllegalArgumentException("User is not found"));

    user.setArticles(articleClient.getArticleResponsesByUser(user.getId()));
    return mapToDto(user);
  }

  public UserAuthResponseDto getUserByEmailForAuth(String email) {
    User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new IllegalArgumentException("User is not found"));

    user.setArticles(articleClient.getArticleResponsesByUser(user.getId()));
    return UserAuthResponseDto.builder()
            .id(user.getId())
            .email(user.getEmail())
            .firstName(user.getFirstName())
            .lastName(user.getLastName())
            .password(user.getPassword())
            .role(user.getRole())
            .build();
  }

  public List<EntityUserResponseDto> getAllUsers() {
    List<User> users = userRepository.findAll();

    return users.stream()
            .peek(user -> user.setArticles(articleClient.getArticleResponsesByUser(user.getId())))
            .map(this::mapToDto)
            .collect(Collectors.toList());
  }

  public List<EntityUserResponseDto> findUsersWithSort(String field, String dirVal){
    Sort.Direction direction = (dirVal != null && dirVal.equalsIgnoreCase("desc"))
            ? Sort.Direction.DESC : Sort.Direction.ASC;

    return userRepository.findAll(Sort.by(direction, field)).stream()
            .peek(user -> user.setArticles(articleClient.getArticleResponsesByUser(user.getId())))
            .map(this::mapToDto)
            .collect(Collectors.toList());
  }

  public Integer saveUser(UserRequestDto userRequest) {
    // TODO check if there is user with this email

    // TODO if password is not easy
    User user = User.builder()
        .firstName(userRequest.getFirstName())
        .lastName(userRequest.getLastName())
        .email(userRequest.getEmail())
        .password(userRequest.getPassword())
        .role("USER")
        .build();
    User savedUser = userRepository.save(user);
    return savedUser.getId();
  }

  public EntityUserResponseDto updateUser(Integer id, UserRequestDto userRequestDto) {
    User fetchedUser = userRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("User is not found"));

    // TODO check if first name is valid
    fetchedUser.setFirstName(userRequestDto.getFirstName());
    // TODO check if last name is valid
    fetchedUser.setLastName(userRequestDto.getLastName());
    // TODO check if email is valid
    fetchedUser.setEmail(userRequestDto.getEmail());

    if(userRequestDto.getPassword() != null){
      // TODO if password is not the same and not easy
      fetchedUser.setPassword(userRequestDto.getPassword());
    }

    userRepository.saveAndFlush(fetchedUser);

    fetchedUser.setArticles(articleClient.getArticleResponsesByUser(fetchedUser.getId()));

    return mapToDto(fetchedUser);
  }

  public boolean deleteUser(Integer id) {
    userRepository.deleteById(id);
    return userRepository.findById(id).isEmpty();
  }

  public UserResponseDto getUserResponse(Integer id) throws CustomUserException {
    User userInDb = userRepository.findById(id)
            .orElseThrow(() -> new CustomUserException("User is not found"));
    return UserResponseDto.builder()
            .id(userInDb.getId())
            .firstName(userInDb.getFirstName())
            .lastName(userInDb.getLastName())
            .build();
  }
}
