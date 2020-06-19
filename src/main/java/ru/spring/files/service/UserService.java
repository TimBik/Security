package ru.spring.files.service;

public interface UserService {
    boolean checkUserByPassword(String password1, String password2);
}
