package com.graduation.onlineclass.controller;


import com.graduation.onlineclass.entity.RespBean;
import com.graduation.onlineclass.entity.TeachingEach;
import com.graduation.onlineclass.service.impl.TeachingEachServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 周富雄
 * @since 2023-05-01
 */
@RestController
@RequestMapping("/teachingEach")
@Api(tags = "单次授课模块")
public class TeachingEachController {

    @Autowired
    TeachingEachServiceImpl teachingEachService;

    @ApiOperation("传入tid,课堂名，插入一个teachingEach")
    @GetMapping("/insert")
    public RespBean insert(Long tid,String title) {
        TeachingEach teachingEach =  new TeachingEach(null,tid,title,null,new Date());
        teachingEachService.save(teachingEach);
        return RespBean.ok("获取成功",teachingEach);
    }

    @ApiOperation("传入teachingId,获取所有课堂信息")
    @GetMapping("/getByTid")
    public RespBean getByTid(Long tid) {

        return RespBean.ok("获取成功",teachingEachService.getByTid(tid));
    }
}

