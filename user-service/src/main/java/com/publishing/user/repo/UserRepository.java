package com.publishing.user.repo;

import java.util.Optional;

import com.publishing.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
  Optional<User> findByEmail(String email);
}
