package com.graduation.onlineclass.controller;

import com.graduation.onlineclass.entity.RespBean;
import com.graduation.onlineclass.entity.UserInfo;
import com.graduation.onlineclass.service.impl.AccountInfoServiceImpl;
import com.graduation.onlineclass.service.impl.UserInfoServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
    @Autowired
    AccountInfoServiceImpl accountInfoService;
    @ApiOperation("登录模块，需要传入一个wx的code")
    @GetMapping("/login")
    public RespBean login(String wxCode){
        if(userInfoService.login(wxCode)==0){
            return RespBean.error("登录失败");
        }
        return RespBean.ok("登录成功");
    }
    @ApiOperation("绑定学校的身份信息")
    @PostMapping("/bindAccount")
    public RespBean bindAccount(@RequestBody Map<String,Object> map){
        String wxCode= (String) map.get("wx_code");
        map.remove("wx_code");
        if(accountInfoService.isExistent(map)){
            System.out.println("存在！");
            System.out.println(map);
            UserInfo userInfo = new UserInfo(wxCode, (String) map.get("u_id"));
            System.out.println(userInfo);
            if(userInfoService.updateById(userInfo)){
                return RespBean.ok("绑定成功！");
            }else{
                return RespBean.error("服务器内部错误，请稍后重试！");
            }

        }else{
            return RespBean.error("输入的绑定信息有误！");
        }
    }
}

