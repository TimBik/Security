package ru.spring.files.service;

import ru.spring.files.dto.UserDto;
import ru.spring.files.model.User;

import java.util.Optional;

public interface SignInService {
    Optional<User> signIn(UserDto userDto);
}
