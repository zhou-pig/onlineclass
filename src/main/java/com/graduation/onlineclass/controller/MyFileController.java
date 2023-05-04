/**
 * -*- codeing = utf-8 -*-
 *
 * @Time :2023/1/29 14:36
 * @Author :zhou_pig
 * @File :FileController.java
 * @Software :IntelliJ IDEA
 */
package com.graduation.onlineclass.controller;

import com.graduation.onlineclass.entity.MyFile;
import com.graduation.onlineclass.entity.RespBean;
import com.graduation.onlineclass.service.impl.MyFileServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;

@RestController
@RequestMapping("/file")
@Api(tags = "文件模块")
public class MyFileController {

    @Value("${filepath}")
    private String baseFilePath;
    @Autowired
    MyFileServiceImpl myFileService;

    @GetMapping("/get")
    @ApiOperation("获得ppt文件")
    public void getFile(String fileName, HttpServletResponse response) {
        System.out.println("getFile:" + fileName);
        String filePath = baseFilePath + fileName;//文件路径
        System.out.println(filePath);
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
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
    @ApiOperation("保存文件")
    public RespBean saveFile(@RequestParam("fileName") String fileName, @RequestParam("uid") Long uid,
                             @RequestParam("teachingEachId") Long teachingEachId,
                             @RequestParam("file") MultipartFile file) {

        //判断文件是否已经存在，存在则不能再上传
        File tempFile = new File(baseFilePath, fileName);
        if (tempFile.exists())
            return RespBean.error("文件已经存在！");
        //创建新的文件
        File newFile = new File(baseFilePath, fileName);
        //获取文件后悔
        int idx = fileName.indexOf(".");
        String type = fileName.substring(idx + 1);
        //将文件输出到目标的文件中
        try {
            file.transferTo(newFile);
            //创建数据库对象
            MyFile myFile = new MyFile(fileName, null, type, new Date(), uid, teachingEachId, baseFilePath, newFile.length());
            if (myFileService.save(myFile)) {
                return RespBean.ok("上次成功");
            } else {
                //删除保存的文件
                newFile.delete();
                return RespBean.error("上次失败，请重试");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return RespBean.error("上次失败，请重试");
        }
    }

    @GetMapping("/getFile")
    @ApiOperation("传入teachingEachId，获得ppt")
    public RespBean getFile(Long teachingEachId, String type) {
        return RespBean.ok("获取成功", myFileService.getFile(teachingEachId,type));
    }

    @GetMapping("/deleteFileById")
    @ApiOperation("传入teachingEachId，获得ppt")
    public RespBean deleteFileById(Long id) {
        if (myFileService.deleteFileById(id))
            return RespBean.ok("删除成功");
        return RespBean.error("删除失败");
    }
}


/*
这是一个用于测试视频！

 */
