package ru.spring.files.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.spring.files.service.EmailService;

@Aspect
@Component
public class FileSenderMailAspect {

    @Autowired
    private EmailService emailService;

    @AfterReturning(pointcut = "execution(* ru.spring.files.service.FileService.uploadFile(*,*,*))",
            returning = "name")
    public void after(JoinPoint joinPoint, Object name) {
        Object[] args = joinPoint.getArgs();
        emailService.sendEmail("File", (String) args[1], (String) name, (String) args[2], "file_email.ftl");
    }

}
