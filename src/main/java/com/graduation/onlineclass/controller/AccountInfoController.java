package com.graduation.onlineclass.controller;

import com.graduation.onlineclass.entity.AccountInfo;
import com.graduation.onlineclass.entity.RespBean;
import com.graduation.onlineclass.service.impl.AccountInfoServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

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

    @ApiOperation("通过id，获取账号信息")
    @GetMapping("/get")
    public RespBean getAccountInfoById(Long id) {
       return RespBean.ok("获取成功",accountInfoService.getById(id));
    }

    @ApiOperation("添加账号信息")
    @PostMapping("/insert")
    public RespBean insertAccountInfo(@RequestBody AccountInfo accountInfo) {
        if (accountInfoService.isExisted(accountInfo)) {
            return RespBean.error("已经存在该用户！");
        }
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
        System.out.println("update!");
        System.out.println(accountInfo);
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

    @ApiOperation("关键字查找（学校，姓名，id），获取所有相关账号信息")
    @GetMapping("/getAllAccountInfoByKey")
    public RespBean getAllAccountInfoByKey(String key) {
        return RespBean.ok("获取成功", accountInfoService.getAllAccountInfoByKey(key));
    }

    @PostMapping("/addUserByFile")
    @ApiOperation("通过文件录入用户信息")
    public RespBean saveFile(HttpServletRequest request, @RequestParam("file") MultipartFile file) {
        try {
            // 获取上传的文件名
            String fileName = file.getOriginalFilename();
            // 获取文件输入流
            InputStream inputStream = file.getInputStream();
            // 读取文件内容
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;
            ArrayList<AccountInfo> failList = new ArrayList<>();
            ArrayList<AccountInfo> successList = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                // 处理每一行的文件内容，比如输出到控制台
                System.out.println(line);
                String[] split = line.split(" ");
                String university = split[0];
                String role = split[1];
                String name = split[2];
                String account = split[3];
                String password = split[4];
                AccountInfo info = new AccountInfo(account, password, university, role, name,null, null);
                if (accountInfoService.isExisted(info) || !accountInfoService.save(info)) {//未录入成功
                    failList.add(info);
                } else {
                    successList.add(info);
                }
            }
            HashMap<String, Object> map = new HashMap<>();
            map.put("failList", failList);
            map.put("successList", successList);
            return RespBean.ok("录入成功", map);
        } catch (IOException e) {
            e.printStackTrace();
            return RespBean.error("文件读取错误");
        }
    }
}
