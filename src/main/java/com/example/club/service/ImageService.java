package com.example.club.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface ImageService {
    int getMaxImageId();
    int saveImage(MultipartFile file) throws IOException;
    byte[] getImage(String imageName) throws IOException;
}
