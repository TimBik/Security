package ru.spring.files.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface FileService {
    String uploadFile(MultipartFile file, String email, String name);
    File downloadFile(String path);
}
