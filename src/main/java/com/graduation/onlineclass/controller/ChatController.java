package com.graduation.onlineclass.controller;


import com.graduation.onlineclass.entity.Chat;
import com.graduation.onlineclass.entity.RespBean;
import com.graduation.onlineclass.service.impl.ChatServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 周富雄
 * @since 2023-01-16
 */
@RestController
@Api(tags = "聊天模块")
@RequestMapping("/chat")
public class ChatController {
    @Autowired
    ChatServiceImpl chatService;

    @ApiOperation("传入一个chat对象插入（无需chatId，发送时间）")
    @PostMapping("/insertChat")
    public RespBean insertChat(@RequestBody Chat chat) {
        chat.setSendTime(new Date());
        if (chatService.save(chat)) {
            return RespBean.ok("信息发送成功");
        }
        return RespBean.error("信息发送失败，请重试");
    }
}
