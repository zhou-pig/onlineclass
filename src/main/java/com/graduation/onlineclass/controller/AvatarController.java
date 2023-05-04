/**
 * -*- codeing = utf-8 -*-
 *
 * @Time :2023/5/4 21:22
 * @Author :zhou_pig
 * @File :AvatarController.java
 * @Software :IntelliJ IDEA
 */
package com.graduation.onlineclass.controller;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.util.Random;

import io.swagger.annotations.Api;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "头像模块")
public class AvatarController {
    @GetMapping("/avatar")
    public ResponseEntity<byte[]> generateAvatar(@RequestParam("username") String username) throws IOException {
        BufferedImage image = new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();
        graphics.setBackground(Color.WHITE);
        graphics.clearRect(0, 0, image.getWidth(), image.getHeight());
        Font font = new Font(Font.SANS_SERIF, Font.BOLD, 100);
        graphics.setFont(font);
        Random random = new Random();
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);
        graphics.setColor(new Color(r,g,b));
        graphics.drawString(getInitials(username), 50, 130);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "png", outputStream);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<>(outputStream.toByteArray(), headers, HttpStatus.OK);
    }

    private String getInitials(String name) {
        String[] parts = name.split("\\s+");
        StringBuilder initials = new StringBuilder();
        for (String part : parts) {
            if (!part.isEmpty()) {
                initials.append(part.substring(0, 1));
            }
        }
        return initials.toString().toUpperCase();
    }
}
