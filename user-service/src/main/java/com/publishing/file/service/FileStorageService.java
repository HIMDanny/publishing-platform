package com.publishing.file.service;

import com.publishing.file.exception.FileStorageException;
import com.publishing.util.FileStorageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageService {
    private final FileStorageProperties fileStorageProperties;
    private Path fileStorageLocation;

    @Autowired
    public FileStorageService(FileStorageProperties fileStorageProperties){
        this.fileStorageProperties = fileStorageProperties;

        this.fileStorageLocation = Paths.get(fileStorageProperties.getAbsoluteUploadDir())
                .toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex){
            throw new FileStorageException("Could not create the directory where the upload files will be stored", ex);
        }
    }

    private void createDirectory(Integer userId){
        this.fileStorageLocation = Paths.get(
                        fileStorageProperties.getAbsoluteUploadDir() + "/" + userId)
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex){
            throw new FileStorageException("Could not create the directory where the upload files will be stored", ex);
        }
    }

    public String storeFile(Integer userId, MultipartFile file){
        createDirectory(userId);

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try{
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException ex){
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public Resource loadFileAsResource(Integer userId, String fileName){
        try {
            Path filePath = this.fileStorageLocation.resolve(userId + "/" + fileName).normalize();
            System.out.println(fileStorageLocation.toAbsolutePath());
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()){
                return resource;
            } else {
                throw new FileStorageException("File not found " + fileName);
            }
        } catch (MalformedURLException ex){
            throw new FileStorageException("File not found " + fileName);
        }
    }
}