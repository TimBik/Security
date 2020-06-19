package ru.spring.files.dto;

import lombok.Data;

@Data
public class SignUpDto {
    private String name;
    private String password;
    private String email;
}
