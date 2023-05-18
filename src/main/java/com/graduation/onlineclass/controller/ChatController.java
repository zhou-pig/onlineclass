package com.graduation.onlineclass.controller;

import com.graduation.onlineclass.entity.Chat;
import com.graduation.onlineclass.entity.RespBean;
import com.graduation.onlineclass.service.impl.ChatServiceImpl;
import com.graduation.onlineclass.service.impl.PermissionServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @Autowired
    PermissionServiceImpl permissionService;

    @ApiOperation("传入一个chat对象插入（无需chatId，发送时间）")
    @PostMapping("/insertChat")
    public RespBean insertChat(@RequestBody Chat chat) {
        Long id = chat.getSender();
        if(permissionService.getById(id).getChat()==0){
            return RespBean.error("你已被禁言");
        }
        chat.setSendTime(new Date());
        if (chatService.save(chat)) {
            return RespBean.ok("信息发送成功");
        }
        return RespBean.error("信息发送失败，请重试");
    }

    @ApiOperation("传入一个用户的id，获取其所有聊天记录")
    @GetMapping("/getChatList")
    public RespBean getChatList(Long id) {
        return RespBean.ok("聊天记录获取成功",chatService.getChatListById(id));
    }

    @ApiOperation("传入一个用户的id，获取其所有聊天记录")
    @GetMapping("/getChatListByTwoId")
    public RespBean getChatListByTwoId(Long id1,Long id2) {
        return RespBean.ok("聊天记录获取成功",chatService.getChatListByTwoId(id1,id2));
    }

    @ApiOperation("传入receiver,sender,聊天记录设为已读")
    @GetMapping("/setAllIsRead")
    public RespBean setAllIsRead(Long receiver,Long sender) {
        return RespBean.ok("更新成功！",chatService.setAllIsRead(receiver,sender));
    }
}

