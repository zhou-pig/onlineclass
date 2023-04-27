/**
 * -*- codeing = utf-8 -*-
 *
 * @Time :2023/1/29 14:36
 * @Author :zhou_pig
 * @File :FileController.java
 * @Software :IntelliJ IDEA
 */
package com.graduation.onlineclass.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RestController
@RequestMapping("/file")
@Api(tags = "文件模块")
public class FileController {

    @Value("${filepath}")
    private String baseFilePath;

    @GetMapping("/get")
    @ApiOperation("获得ppt文件")
    public void getFile(String fileName, HttpServletResponse response) {
        System.out.println("getFile:" + fileName);
        String filePath = baseFilePath + fileName;//文件路径
        System.out.println(filePath);
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
//        response.setHeader("Content-Disposition", "test.ppt");//文件名
        response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");

        File file = new File(filePath);
        if (file.exists() && file.isFile()) {
            try {
                InputStream inputStream = new FileInputStream(file);
                OutputStream outputStream = response.getOutputStream();//获取response的输出流对象
                IOUtils.copy(inputStream, outputStream);
                //刷新输出流
                outputStream.flush();
                //关闭输出流
                outputStream.close();
                inputStream.close();
                return;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        response.setStatus(500);
    }

    @PostMapping("/save")
    @ApiOperation("保存ppt文件")
    public void saveFile(HttpServletRequest request, @RequestParam("file") MultipartFile file) {
        //获取文件名
        String fileName = request.getParameter("fileName");
        System.out.println("saveFile:" + fileName);
        //创建新的文件
        //linux
//        File newFile = new File("var/blob/guli_user",uuidFilename);
        //windows
        File newFile = new File(baseFilePath, fileName);
        //将文件输出到目标的文件中
        try {
            file.transferTo(newFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
