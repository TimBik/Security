package ru.spring.files.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.spring.files.dto.UserDto;
import ru.spring.files.model.User;
import ru.spring.files.repositories.UserRepositories;

import java.util.Optional;

public class SignInServiceImpl implements SignInService {

    @Autowired
    private UserRepositories userRepositories;

    @Autowired
    private UserService userService;

    @Override
    public Optional<User> signIn(UserDto userDto) {
        String login = userDto.getEmail();
        String password = userDto.getPassword();
        Optional<User> user = userRepositories.getUserByLogin(login);
        if (user.isPresent() && userService.checkUserByPassword(password, user.get().getPassword())) {
            return user;
        }
        return Optional.empty();
    }
}
