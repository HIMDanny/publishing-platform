package com.publishing.user;

import com.publishing.clients.auth.RegisterRequest;
import com.publishing.clients.user.Role;
import com.publishing.clients.user.User;
import com.publishing.exception.CustomUserException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  public User getUserById(Integer id) {
    // TODO: add custom exception
    return userRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("User is not found"));
  }


  public User getUserByEmail(String email) {
    return userRepository.findByEmail(email)
        .orElseThrow(() -> new IllegalArgumentException("User is not found"));
  }

  public boolean saveUser(RegisterRequest userRequest) {
    User user = User.builder()
        .firstName(userRequest.getFirstName())
        .lastName(userRequest.getLastName())
        .email(userRequest.getEmail())
        .password(userRequest.getPassword())
        .role(Role.USER)
        .build();
    User savedUser = userRepository.save(user);
    return savedUser.getId() != 0;
  }

  public User updateUser(Integer id, User user) {
    User fetchedUser = userRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("User is not found"));
    user.setId(fetchedUser.getId());
    userRepository.saveAndFlush(user);
    return user;
  }

  public boolean deleteUser(Integer id) {
    userRepository.deleteById(id);
    return getUserById(id).getId() == 0;
  }

  public User getUserResponse(Integer id) throws CustomUserException {
    return userRepository.findById(id)
            .orElseThrow(() -> new CustomUserException("User is not found"));
  }
}
