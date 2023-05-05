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
import com.graduation.onlineclass.service.impl.TeachingEachServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/file")
@Api(tags = "文件模块")
public class MyFileController {

    @Value("${filepath}")
    private String baseFilePath;
    @Autowired
    MyFileServiceImpl myFileService;
    @Autowired
    TeachingEachServiceImpl teachingEachService;

    @GetMapping("/getById")
    @ApiOperation("传入id,获得ppt文件")
    public void getById(Long id, HttpServletResponse response) {
        MyFile myFile = myFileService.getById(id);
        String filePath = baseFilePath + myFile.getName();//文件路径
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
        System.out.println("查找成功！" + filePath);
        File file = new File(filePath);
        if (file.exists() && file.isFile()) {
            try {
                System.out.println("文件存在！");
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
        } else {
            System.out.println("exist:" + file.exists());
            System.out.println("isFile:" + file.isFile());
            response.setStatus(500);
        }
    }

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
                             @RequestParam("file") MultipartFile file,
                             HttpServletResponse response) {

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
                response.setStatus(500);
                return RespBean.error("上次失败，请重试");
            }
        } catch (IOException e) {
            e.printStackTrace();
            response.setStatus(500);
            return RespBean.error("上次失败，请重试");
        }
    }

    @GetMapping("/getFile")
    @ApiOperation("传入teachingEachId，获得ppt")
    public RespBean getFile(Long teachingEachId, String type) {
        return RespBean.ok("获取成功", myFileService.getFile(teachingEachId, type));
    }

    @GetMapping("/deleteFileById")
    @ApiOperation("传入teachingEachId，获得ppt")
    public RespBean deleteFileById(Long id) {
        if (myFileService.deleteFileById(id))
            return RespBean.ok("删除成功");
        return RespBean.error("删除失败");
    }

    @GetMapping("/getScoreFile")
    @ApiOperation("传入teachingEachId，以excel形式返回学生成绩")
    public void downloadExcel(@RequestParam("teachingEachId") Long teachingEachId, HttpServletResponse response) throws IOException {
        //获取学生成绩
        List<List<Object>> scoreList = teachingEachService.getScoreSimple(teachingEachId);
        // 创建一个工作簿对象
        Workbook workbook = new XSSFWorkbook();
        // 在工作簿中创建一个工作表
        Sheet sheet = workbook.createSheet("学生成绩");
        // 在第一行创建标题行
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("学生姓名");
        for (int i = 0; i < scoreList.get(0).size() - 2; i++) {
            headerRow.createCell(i + 1).setCellValue("第" + (i + 1) + "题");
        }
        headerRow.createCell(scoreList.get(0).size() - 1).setCellValue("总分");

        // 写入数据
        int rowNum = 1;
        for (List<Object> list : scoreList) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue((String) list.get(0));
            for(int j=1;j<list.size();j++)
                row.createCell(j).setCellValue((Integer) list.get(j));
        }
//        // 设置响应头信息，告诉浏览器返回的是一个文件流
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=studentScore.xlsx");
        response.addHeader("Access-Control-Expose-Headers", "Content-Disposition");

        // 将数据写入响应体中
        OutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.flush();
    }
}
