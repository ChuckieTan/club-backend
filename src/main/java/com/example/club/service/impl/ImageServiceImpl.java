package com.example.club.service.impl;

import com.example.club.mapper.ImageMapper;
import com.example.club.model.Image;
import com.example.club.service.ImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Service
public class ImageServiceImpl implements ImageService {

    @Resource
    ImageMapper imageMapper;

    public static String imagePath;

    @Value("${web.upload-path}")
    public void setImagePath(String imagePath) {
        ImageServiceImpl.imagePath = imagePath;
    }

    static boolean isInit = false;

    static Logger logger = LoggerFactory.getLogger(ImageServiceImpl.class);

    public static void init() {
        if(!isInit) {
            System.out.println("图片路径" + imagePath);
            File folder = new File(imagePath);
            if (!folder.exists() && !folder.isDirectory()) {
                if (folder.mkdirs()) {
                    logger.info("创建图片目录: " + imagePath);
                } else {
                    logger.error("无法创建图片目录: " + imagePath);
                }
            }
            isInit = true;
        }
    }

    @Override
    public int getMaxImageId() {
        init();
        return imageMapper.selectMaxPrimaryKey();
    }

    @Override
    public String saveImage(MultipartFile file)
            throws IOException {
        init();
        String fileName = UUID.randomUUID().toString() + ".jpg";
        File dest = new File(imagePath + fileName);
        file.transferTo(dest);
        imageMapper.insertSelective(new Image(null, fileName));
        return fileName;
    }

    @Override
    public byte[] getImage(String imageName) throws IOException {
        init();
        File file = new File(imagePath + imageName);
        FileInputStream inputStream = new FileInputStream(file);
        byte[] bytes = new byte[inputStream.available()];
        int read = inputStream.read(bytes, 0, inputStream.available());
        return new byte[0];
    }
}
