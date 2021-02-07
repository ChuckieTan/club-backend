package com.example.club.controller;

import com.example.club.service.ImageService;
import com.example.club.util.ResultType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

@Controller
public class ImageController {
    @Resource
    ImageService imageService;

    Logger logger = LoggerFactory.getLogger(ImageController.class);

    @PostMapping(value = "/image")
    @ResponseBody
    public ResultType<Object> upload(@RequestParam(value = "image") MultipartFile file) {
        ResultType<Object> result = null;
        if (file.isEmpty()) {
            result = new ResultType<>(-1, "上传失败，请重新选择文件", null);
        } else {
            try {
                int ImageId = imageService.saveImage(file);
                logger.info("上传成功");
                result = new ResultType<>(1, "上传成功", ImageId + ".jpg");
            } catch (Exception e) {
                logger.error(e.toString());
                logger.info("上传失败");
                result = new ResultType<>(1, "上传失败", null);
            }
        }
        return result;
    }

    @GetMapping(value = "/image/{image-name}", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] getPicture(@PathVariable("image-name") String imageName)
            throws IOException {
        byte[] result = null;
        try {
            result = imageService.getImage(imageName);
        } catch (IOException e) {
            logger.error("非法文件访问: "+ imageName);
        }
        logger.info("访问图片: " + imageName);
        return result;
    }
}
