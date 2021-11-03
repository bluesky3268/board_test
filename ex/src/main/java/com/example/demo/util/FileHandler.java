package com.example.demo.util;

import com.example.demo.entity.UploadFile;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Component
public class FileHandler {

    @Value("${file.dir}")
    private String fileDir;

    public String getFullPath(String fileName) {
        return fileDir + fileName;
    }

    public UploadFile saveFile(MultipartFile multipartFile) throws IOException {
        if (!multipartFile.isEmpty()) {
            String originalFilename = multipartFile.getOriginalFilename();

            int p = originalFilename.lastIndexOf(".");
            String extension = originalFilename.substring(p);

            String uuid = UUID.randomUUID().toString();
            String saveFileName = uuid + "." + extension;

            multipartFile.transferTo(new File(getFullPath(saveFileName)));
            return new UploadFile(originalFilename, saveFileName);
        }
        return null;

    }

    public UploadFile saveImg(MultipartFile multipartFile) throws IOException {
        if (!multipartFile.isEmpty()) {
            String originalFilename = multipartFile.getOriginalFilename();

            int p = originalFilename.lastIndexOf(".");
            String extension = originalFilename.substring(p);

            String uuid = UUID.randomUUID().toString();
            String saveFileName = uuid + "." + extension;

            multipartFile.transferTo(new File(getFullPath(saveFileName)));
            return new UploadFile(originalFilename, saveFileName);
        }
        return null;

    }

}