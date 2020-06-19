package ru.spring.files.service;

public interface EmailService {
    void sendEmail(String subject, String email, String confirmCode, String userName, String ftl) ;
}
