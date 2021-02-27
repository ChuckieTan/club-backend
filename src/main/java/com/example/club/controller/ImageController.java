package com.example.club.controller;

import com.example.club.service.ImageService;
import com.example.club.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

//@Transactional
@Controller
public class ImageController {
    @Resource
    ImageService imageService;

    Logger logger = LoggerFactory.getLogger(ImageController.class);

    @PostMapping(value = "/image")
    @ResponseBody
    public Result upload(@RequestParam(value = "img") MultipartFile file) {
        Result result;
        if (file.isEmpty()) {
            result = new Result(-1, "上传失败，请重新选择文件", null);
        } else {
            try {
                int imageId = imageService.saveImage(file);
                logger.info("上传成功");
                result = new Result(1, "上传成功", imageId);
            } catch (Exception e) {
                logger.error(e.toString());
                logger.info("上传失败");
                result = new Result(-1, "上传失败", null);
            }
        }
        return result;
    }

    @ResponseBody
    @Transactional(readOnly = true)
    @GetMapping(value = "/image/{image-id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getImage(@PathVariable("image-id") Integer imageId) {
        byte[] result = null;
        try {
            result = imageService.getImage(imageId);
        } catch (Exception e) {
            logger.error("非法文件访问: " + imageId);
        }
        logger.info("访问图片: " + imageId);
        return result;
    }
}
