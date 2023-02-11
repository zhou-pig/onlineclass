package com.graduation.onlineclass.controller;


import com.graduation.onlineclass.entity.AccountInfo;
import com.graduation.onlineclass.entity.RespBean;
import com.graduation.onlineclass.service.impl.AccountInfoServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 周富雄
 * @since 2023-01-16
 */
@Api(tags = "账号信息模块")
@RestController
@RequestMapping("/accountInfo")
public class AccountInfoController {
    @Autowired
    AccountInfoServiceImpl accountInfoService;

    @ApiOperation("添加账号信息")
    @PostMapping("/insert")
    public RespBean insertAccountInfo(@RequestBody AccountInfo accountInfo) {
        if (accountInfoService.save(accountInfo))
            return RespBean.ok("添加成功！");
        else
            return RespBean.error("添加失败，请重试！");
    }

    @ApiOperation("删除账号信息")
    @GetMapping("/deleteById")
    public RespBean deleteAccountInfoById(Long id) {
        if (accountInfoService.removeById(id))
            return RespBean.ok("删除成功！");
        else
            return RespBean.error("删除失败，请重试！");
    }

    @ApiOperation("更新账号信息")
    @PostMapping("/update")
    public RespBean updateAccountInfo(@RequestBody AccountInfo accountInfo) {
        if (accountInfoService.updateById(accountInfo))
            return RespBean.ok("修改成功！");
        else
            return RespBean.error("修改失败，请重试！");
    }

    @ApiOperation("获取所有账号信息")
    @GetMapping("/getAll")
    public RespBean getAllAccountInfo() {
        return RespBean.ok("获取成功", accountInfoService.getAccountInfoByMap(null));
    }
}

