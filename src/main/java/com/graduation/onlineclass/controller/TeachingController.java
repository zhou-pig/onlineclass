package com.graduation.onlineclass.controller;


import com.graduation.onlineclass.entity.RespBean;
import com.graduation.onlineclass.service.impl.TeachingServiceImpl;
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
@RequestMapping("/teaching")
@Api(tags = "老师授课信息模块")
public class TeachingController {
    @Autowired
    TeachingServiceImpl teachingService;

    @ApiOperation("传入老师号，获取所有授课信息")
    @GetMapping("/getCourseList")
    public RespBean getCourseList(Long uId){
        return RespBean.ok("获取成功",teachingService.getCourseListByuId(uId));
    }

}

