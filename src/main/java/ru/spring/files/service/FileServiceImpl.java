package ru.spring.files.service;

import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Random;


@PropertySource("classpath:properties/application.properties")
public class FileServiceImpl implements FileService {
    @Autowired
    private Environment environment;

    @Override
    public String uploadFile(MultipartFile file, String email, String name) {
        String dir = environment.getProperty("storage.path");
        String nameFile ="";
        try {
            nameFile = file.getOriginalFilename();
            String[] data = nameFile.split("\\.");
            File dirFile = new File(dir + File.separator);
            if (!dirFile.exists()) {
                dirFile.mkdirs();
            }
            File loadFile = new File(dirFile.getAbsolutePath() + File.separator + new Random().nextInt() + "." + data[1]);
            file.transferTo(loadFile);
            return loadFile.getName();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public File downloadFile(String path) {
        String name = environment.getProperty("storage.path") + "\\" + path;
        return new File(name);
    }

}
