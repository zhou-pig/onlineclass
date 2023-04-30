package com.graduation.onlineclass.controller;

import com.graduation.onlineclass.entity.AccountInfo;
import com.graduation.onlineclass.entity.RespBean;
import com.graduation.onlineclass.entity.UserInfo;
import com.graduation.onlineclass.service.impl.AccountInfoServiceImpl;
import com.graduation.onlineclass.service.impl.UserInfoServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
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

    @ApiOperation("登录模块，需要传入一个wx的code，保存在session中")
    @GetMapping("/login")
    public RespBean login(String wxCode, HttpServletRequest request) {
        System.out.println("wxCode:" + wxCode);
        HttpSession session = request.getSession();
        session.setAttribute("wxCode", wxCode);
//        if(userInfoService.login(wxCode)==0){
//            return RespBean.error("登录失败");
//        }
        return RespBean.ok("登录成功");
    }

    @ApiOperation("绑定用户的身份信息")
    @PostMapping("/bindAccount")
    public RespBean bindAccount(@RequestBody Map<String, Object> map) {
        String wxCode = (String) map.get("wx_code");
        map.remove("wx_code");
        List<AccountInfo> accountInfoList = accountInfoService.getAccountInfoByMap(map);
        if (accountInfoList.size() > 0) {
            //判断该账号是否已被绑定
            Long uId = accountInfoList.get(0).getId();
            System.out.println("存在！,uId=" + uId);
            System.out.println(map);
            System.out.println("准备创建userInfo");
            UserInfo userInfo = new UserInfo(wxCode, uId);
            System.out.println("userInfo创建成功");
            System.out.println(userInfo);
            if (userInfoService.getById(uId).getWxCode() != null) {
                return RespBean.error("该账号已被绑定，请联系管理员！");
            }
            //在绑定之前，先把之前绑定该wx_code的账号数据设置为null
            UserInfo u = userInfoService.getByWxCode(wxCode);
            if (u != null) {
                System.out.println(u);
                userInfoService.setWxCode(u.getUId(),null);
            }
            if (userInfoService.updateById(userInfo)) {
                return RespBean.ok("绑定成功！", uId);//返回uid
            } else {
                //绑定失败，回滚
                if (u != null) {
                    userInfoService.setWxCode(u.getUId(),wxCode);
                }
                return RespBean.error("服务器内部错误，请稍后重试！");
            }
        } else {
            return RespBean.error("输入的绑定信息有误！");
        }
    }
}
