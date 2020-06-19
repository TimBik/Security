package ru.spring.files.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ru.spring.files.dto.SignUpDto;
import ru.spring.files.service.FileService;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Controller
public class FileController {

    @Autowired
    private FileService fileService;

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ModelAndView loadFile() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("profile");
        return modelAndView;
    }

    @RequestMapping(value = "/files", method = RequestMethod.POST)
    public ModelAndView uploadFile(@RequestParam("file") MultipartFile file, HttpSession session) {
        if (session.getAttribute("user") != null) {
            SignUpDto signUpDto = (SignUpDto) session.getAttribute("user");
            fileService.uploadFile(file, signUpDto.getEmail(), signUpDto.getName());
        }
        return null;
    }

    @RequestMapping(value ="/files/{file-name:.+}" , method = RequestMethod.GET)
    public ModelAndView getFile(@PathVariable("file-name") String fileName, HttpServletResponse response) {
        File file = fileService.downloadFile(fileName);
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        response.setContentType("application/vnd.ms-excel");
        try {
            Files.copy(file.toPath(), response.getOutputStream());
            response.getOutputStream().flush();
        } catch (IOException e) {
            throw new RuntimeException("IOError writing file to output stream");
        }
        return null;
    }
}
