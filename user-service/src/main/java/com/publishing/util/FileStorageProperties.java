package com.publishing.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;

@Component
public class FileStorageProperties {

    @Autowired
    private ResourceLoader resourceLoader;
    @Value("${file.upload-dir}")
    private String uploadDir;

    public String getUploadDir() {
        return uploadDir.replace("static", "");
    }

    public String getAbsoluteUploadDir(){
        String classpath = getClass().getClassLoader().getResource(uploadDir).getPath();
        return classpath;
    }

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }
}