package ru.spring.files.service;


import java.util.Map;

public interface MessageCreate {
    String createMessageFtl(String name, Map<String, Object> root);
}
