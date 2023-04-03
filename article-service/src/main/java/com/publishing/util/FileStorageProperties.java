package com.publishing.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;

@Component
public class FileStorageProperties {
    @Value("${file.upload-dir}")
    private String uploadDir;

    public String getUploadDir() {
        String classpath = getClass().getClassLoader().getResource(uploadDir).getPath();
        return classpath;

    }

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }
}
