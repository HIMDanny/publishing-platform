package com.publishing.user.repo;

import java.util.List;
import java.util.Optional;

import com.publishing.user.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> {
  Optional<User> findByEmail(String email);

  @Query(value = "SELECT u FROM app_user u WHERE u.firstName " +
                 "LIKE CONCAT('%', :value, '%') " +
                 "OR u.lastName LIKE CONCAT('%', :value, '%')")
  List<User> searchUsers(@Param("value") String value);

  @Query(value = "SELECT * FROM app_user u WHERE u.firstName " +
                  "LIKE CONCAT('%', :value, '%') " +
                  "OR u.lastName LIKE CONCAT('%', :value, '%')",
          countQuery = "SELECT count(*) FROM app_user u WHERE u.firstName " +
                  "LIKE CONCAT('%', :value, '%') " +
                  "OR u.lastName LIKE CONCAT('%', :value, '%')",
          nativeQuery = true)
  Page<User> searchUserWithPagination(@Param("value") String value, Pageable pageable);
}
