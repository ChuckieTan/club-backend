package com.example.club.service.impl;

import com.example.club.mapper.ImageMapper;
import com.example.club.model.Image;
import com.example.club.service.ImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

@Service
public class ImageServiceImpl implements ImageService {

    @Resource
    ImageMapper imageMapper;

    static String imagePath;

    static Logger logger = LoggerFactory.getLogger(ImageServiceImpl.class);

    static {
        try {
            imagePath = getRealPath() + "images/";

            File folder = new File(imagePath);
            if (!folder.exists() && !folder.isDirectory()) {
                if(folder.mkdirs()) {
                    logger.info("创建图片目录: " + imagePath);
                } else {
                    logger.error("无法创建图片目录: " + imagePath);
                }
            }
        } catch (UnsupportedEncodingException e) {
            logger.error("获取项目路径出错");
        }

    }

    @Override
    public int getMaxImageId() {
        return imageMapper.selectMaxPrimaryKey();
    }

    public static String getRealPath() throws UnsupportedEncodingException {
        String path = ImageServiceImpl.class.getProtectionDomain().getCodeSource().getLocation().getFile();
        path = java.net.URLDecoder.decode(path, StandardCharsets.UTF_8);
        if (path.contains(".jar")) {
            // 去除file:/和最后的/
            int firstIndex = path.lastIndexOf(System.getProperty("path.separator")) + 1;
            int lastIndex = path.lastIndexOf(".jar") + 1;
            path = path.substring(firstIndex, lastIndex);

            // 去除最后的路径
            lastIndex = path.lastIndexOf(File.separator) + 1;
            firstIndex = 0;
            path = path.substring(firstIndex, lastIndex);
        } else {
            // 去除开始的file
            int firstIndex = path.lastIndexOf(System.getProperty("path.separator")) + 1;
            int lastIndex = path.lastIndexOf(File.separator);
            path = path.substring(firstIndex, lastIndex);

            // 去除路径最后一级
            firstIndex = 0;
            lastIndex = path.lastIndexOf(File.separator) + 1;
            path = path.substring(firstIndex, lastIndex);
        }
        logger.info("项目路径: " + path);
        return path;
    }


    @Override
    public int saveImage(@RequestParam(value = "image") MultipartFile file)
            throws IOException {
        int fileNumber = getMaxImageId() + 1;
        String fileName = fileNumber + ".jpg";
        File dest = new File(imagePath + fileName);
        file.transferTo(dest);
        imageMapper.insert(new Image(fileNumber, fileName));
        return fileNumber;
    }

    @Override
    public byte[] getImage(String imageName) throws IOException {
        File file = new File(imagePath + imageName);
        FileInputStream inputStream = new FileInputStream(file);
        byte[] bytes = new byte[inputStream.available()];
        int read = inputStream.read(bytes, 0, inputStream.available());
        return new byte[0];
    }
}
