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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

@RestController
@RequestMapping("/file")
@Api(tags = "文件模块")
public class FileController {

    @GetMapping("/ppt")
    @ApiOperation("获得ppt文件")
    public void getFile(String fileName,HttpServletResponse response) {
        String FilePath = "C:\\Users\\86182\\Desktop\\"+fileName;//文件路径
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition", "test.ppt");//文件名
        response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");

        File file = new File(FilePath);
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
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
