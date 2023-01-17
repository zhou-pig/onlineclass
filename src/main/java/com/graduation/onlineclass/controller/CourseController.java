package com.graduation.onlineclass.controller;


import com.graduation.onlineclass.entity.RespBean;
import com.graduation.onlineclass.service.impl.CourseServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 周富雄
 * @since 2023-01-16
 */
@RestController
@RequestMapping("/course")
@Api(tags = "课程基本信息模块")
public class CourseController {

    @Autowired
    CourseServiceImpl courseService;

    @ApiOperation("传入课程号，获得课程的基本信息")
    @GetMapping("getCourseInfo")
    public RespBean getCourseInfo(Long cId){
        return RespBean.ok("获取成功！",courseService.getById(cId));
    }
}

