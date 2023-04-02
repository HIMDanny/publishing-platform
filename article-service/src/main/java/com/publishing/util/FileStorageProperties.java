package com.publishing.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;

@Component
public class FileStorageProperties {
    @Value("${file.upload-dir}")
    private String uploadDir;

    public String getUploadDir() {
        try {
            String classpath = ResourceUtils.getFile("classpath:").getAbsolutePath();
            return classpath + File.separator + uploadDir;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }
}
