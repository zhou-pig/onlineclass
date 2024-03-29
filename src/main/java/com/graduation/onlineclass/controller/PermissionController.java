package com.graduation.onlineclass.controller;


import com.graduation.onlineclass.entity.Permission;
import com.graduation.onlineclass.entity.RespBean;
import com.graduation.onlineclass.service.PermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 周富雄
 * @since 2023-04-27
 */
@Api(tags = "权限控制模块")
@RestController
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    PermissionService permissionService;

    @ApiOperation("通过id，获取用户权限")
    @GetMapping("/getById")
    public RespBean getById(Long id){
        return RespBean.ok("用户权限获取成功",permissionService.getById(id));
    }

    @ApiOperation("传入一个权限对象，进行更新")
    @PostMapping("/update")
    public RespBean getById(@RequestBody Permission permission){
        return RespBean.ok("用户权限更新成功",permissionService.updateById(permission));
    }

    @ApiOperation("修改聊天权限,传入用户id和权限0或1")
    @GetMapping("/setChat")
    public RespBean setChat(Long id,int value){
        Permission permission= permissionService.getById(id);
        permission.setChat(value);
        permissionService.updateById(permission);
        return RespBean.ok("用户聊天权限修改成功");
    }

    @ApiOperation("修改讨论权限,传入用户id和权限0或1")
    @GetMapping("/setDiscussion")
    public RespBean setDiscussion(Long id,int value){
        Permission permission= permissionService.getById(id);
        permission.setDiscussion(value);
        permissionService.updateById(permission);
        return RespBean.ok("用户讨论权限修改成功");
    }

    @ApiOperation("停用、激活账号,传入用户id和状态0、1")
    @GetMapping("/setActivated")
    public RespBean setActivated(Long id,int value){
        Permission permission= permissionService.getById(id);
        permission.setActivated(value);
        permissionService.updateById(permission);
        if(value==1)
            return RespBean.ok("账号已激活");
        else
            return RespBean.ok("账号已停用");
    }
}

