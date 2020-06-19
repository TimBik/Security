package ru.spring.files.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private MessageCreate messageCreate;

    @Override
    public void sendEmail(String subject, String email, String confirmCode, String userName, String ftl) {
        Map<String, Object> data = new HashMap<>();
        data.put("userName", userName);
        data.put("confirmCode", confirmCode);
        String html = messageCreate.createMessageFtl(ftl, data);
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());
            messageHelper.setFrom("friend");
            messageHelper.setTo(email);
            messageHelper.setSubject(subject);
            messageHelper.setText(html, true);
        };

        javaMailSender.send(messagePreparator);

    }
}
