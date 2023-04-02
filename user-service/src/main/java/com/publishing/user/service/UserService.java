package com.publishing.user.service;

import com.publishing.clients.PaginationParameters;
import com.publishing.clients.article.ArticleClient;
import com.publishing.clients.user.dto.UserAuthResponseDto;
import com.publishing.clients.user.dto.UserRequestDto;
import com.publishing.clients.user.dto.UserResponseDto;
import com.publishing.exception.CustomUserException;
import com.publishing.exception.EmailNotUniqueException;
import com.publishing.user.dto.EntityUserResponseDto;
import com.publishing.user.dto.UserPageResponseDto;
import com.publishing.user.model.User;
import com.publishing.user.repo.UserRepository;
import com.publishing.util.FileStorageProperties;
import com.publishing.util.UserPaginationParametersValidator;
import jakarta.persistence.PersistenceException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService extends UserServiceCommon {

    private final UserRepository userRepository;
    private final ArticleClient articleClient;
    private final UserPaginationParametersValidator paramsValidator;

    public EntityUserResponseDto getUserById(Integer id, PaginationParameters paginationParameters) throws CustomUserException {

        paginationParameters.setField(paramsValidator.getCorrectValue(paginationParameters.getField()).getHqlField());

        // TODO: add custom exception
        User user = userRepository.findById(id)
                .orElseThrow(() -> new CustomUserException("User is not found"));

        user.setPage(articleClient.getArticleResponsesByUserWithPagination(
                id,
                toMap(paginationParameters)
        ));

        return mapToDto(user);
    }

    public EntityUserResponseDto getUserByEmail(String email) throws CustomUserException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomUserException("User is not found"));

        user.setPage(articleClient.getArticleResponsesByUserWithPagination(
                user.getId(),
                toMap(PaginationParameters.builder()
                        .page(1).pageSize(10).field("numberOfViews").direction("asc").build())
        ));
        return mapToDto(user);
    }

    public UserAuthResponseDto getUserByEmailForAuth(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User is not found"));

        user.setPage(articleClient.getArticleResponsesByUserWithPagination(
                user.getId(),
                toMap(PaginationParameters.builder()
                        .page(1).pageSize(10).field("numberOfViews").direction("asc").build())
        ));
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
                .peek(user -> user.setPage(articleClient.getArticleResponsesByUserWithPagination(
                        user.getId(),
                        toMap(PaginationParameters.builder()
                                .page(1).pageSize(10).field("numberOfViews").direction("asc").build())
                )))
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public List<EntityUserResponseDto> findUsersWithSort(String field, String dirVal) {

        field = field == null ? "id" : field;
        field = paramsValidator.getCorrectValue(field).getHqlField();

        Sort.Direction direction = (dirVal != null && dirVal.equalsIgnoreCase("desc"))
                ? Sort.Direction.DESC : Sort.Direction.ASC;

        return userRepository.findAll(Sort.by(direction, field)).stream()
                .peek(user -> user.setPage(articleClient.getArticleResponsesByUserWithPagination(
                        user.getId(),
                        toMap(PaginationParameters.builder()
                                .page(1).pageSize(10).field("numberOfViews").direction("asc").build())
                )))
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public Integer saveUser(UserRequestDto userRequest, String imageName) throws EmailNotUniqueException {

        // TODO if password is not easy
        User user = User.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .image(imageName)
                .role("USER")
                .build();

        checkEmailForUnique(user.getEmail());

        User savedUser = userRepository.save(user);

        return savedUser.getId();
    }

    public EntityUserResponseDto updateUser(Integer id, UserRequestDto userRequestDto, MultipartFile image) throws CustomUserException, EmailNotUniqueException {
        User fetchedUser = userRepository.findById(id)
                .orElseThrow(() -> new CustomUserException("User is not found"));

        String firstName = userRequestDto.getFirstName();
        if(firstName != null && !firstName.isBlank())
            fetchedUser.setFirstName(firstName);

        String lastName = userRequestDto.getLastName();
        if(lastName != null && !lastName.isBlank())
            fetchedUser.setLastName(lastName);
        String email = userRequestDto.getEmail();
        if(email != null && !email.isBlank())
            fetchedUser.setEmail(email);

        if(image != null)
          fetchedUser.setImage(image.getOriginalFilename());

        if (userRequestDto.getPassword() != null) {
            // TODO if password is not the same and not easy
            fetchedUser.setPassword(userRequestDto.getPassword());
        }

        checkEmailForUnique(fetchedUser.getEmail());

        userRepository.saveAndFlush(fetchedUser);

        fetchedUser.setPage(articleClient.getArticleResponsesByUserWithPagination(
                fetchedUser.getId(),
                toMap(PaginationParameters.builder()
                        .page(1).pageSize(10).field("numberOfViews").direction("asc").build())
        ));

        return mapToDto(fetchedUser);
    }

    public boolean deleteUser(Integer id) throws CustomUserException {
        if(userRepository.findById(id).isEmpty()){
            throw new CustomUserException(String.format("User with id %d cannot be found", id));
        }
        userRepository.deleteById(id);
        return userRepository.findById(id).isEmpty();
    }

    public UserResponseDto getUserResponse(Integer id) throws CustomUserException {
        User userInDb = userRepository.findById(id)
                .orElseThrow(() -> new CustomUserException(String.format("User with id %d cannot be found", id)));
        return UserResponseDto.builder()
                .id(userInDb.getId())
                .firstName(userInDb.getFirstName())
                .lastName(userInDb.getLastName())
                .build();
    }

    public EntityUserResponseDto getUserWithLikedArticles(Integer id, PaginationParameters paginationParameters) throws CustomUserException {

        paginationParameters.setField(paramsValidator.getCorrectValue(paginationParameters.getField()).getHqlField());

        // TODO: add custom exception
        User user = userRepository.findById(id)
                .orElseThrow(() -> new CustomUserException(String.format("User with id %d cannot be found", id)));

        user.setPage(articleClient.getLikedArticlesByUserWithPagination(
                id,
                toMap(paginationParameters)
        ));

        return mapToDto(user);
    }

    public EntityUserResponseDto getUserWithBookmarkedArticles(Integer id, PaginationParameters paginationParameters) throws CustomUserException {

        paginationParameters.setField(paramsValidator.getCorrectValue(paginationParameters.getField()).getHqlField());

        // TODO: add custom exception
        User user = userRepository.findById(id)
                .orElseThrow(() -> new CustomUserException(String.format("User with id %d cannot be found", id)));

        user.setPage(articleClient.getBookmarkedArticlesByUserWithPagination(
                id,
                toMap(paginationParameters)
        ));

        return mapToDto(user);
    }

    private void checkEmailForUnique(String email) throws EmailNotUniqueException {
        if(userRepository.findByEmail(email).isPresent()){
            throw new EmailNotUniqueException(String.format("User with email %s is already registered", email));
        }
    }
}
