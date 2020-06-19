package ru.spring.files.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.spring.files.model.User;
import ru.spring.files.repositories.UserRepositories;

import java.util.Optional;

public class ConfirmServiceImpl implements ConfirmService {

    @Autowired
    private UserRepositories userRepositories;


    @Override
    public boolean updateSate(String email) {
        Optional<User> userOptional = userRepositories.getUserByLogin(email);
        if (userOptional.isPresent()) {
            userRepositories.update(userOptional.get().getConfirmCode());
            return true;
        }
        return false;
    }
}
