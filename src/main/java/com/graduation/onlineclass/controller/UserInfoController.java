package com.graduation.onlineclass.controller;


import com.graduation.onlineclass.entity.RespBean;
import com.graduation.onlineclass.service.UserInfoService;
import com.graduation.onlineclass.service.impl.UserInfoServiceImpl;
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
@RequestMapping("/userInfo")
@Api(tags = "用户信息模板")
public class UserInfoController {
    @Autowired
    UserInfoServiceImpl userInfoService;

    @ApiOperation("登录模块，需要传入一个wx的code")
    @GetMapping("/login")
    public RespBean login(String wxCode){
        if(userInfoService.login(wxCode)==0){
            return RespBean.error("登录失败");
        }
        return RespBean.ok("登录成功");
    }
}

