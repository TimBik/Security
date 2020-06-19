package ru.spring.files.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserServiceImpl implements UserService {


    @Override
    public boolean checkUserByPassword(String password1, String password2) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.matches(password1, password2);
    }
}
