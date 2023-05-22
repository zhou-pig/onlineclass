package com.graduation.onlineclass.controller;


import com.graduation.onlineclass.entity.Discussion;
import com.graduation.onlineclass.entity.RespBean;
import com.graduation.onlineclass.service.impl.AccountInfoServiceImpl;
import com.graduation.onlineclass.service.impl.DiscussionServiceImpl;
import com.graduation.onlineclass.service.impl.PermissionServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 周富雄
 * @since 2023-02-02
 */
@RestController
@RequestMapping("/discussion")
@Api(tags = "讨论区模块")
public class DiscussionController {

    @Autowired
    DiscussionServiceImpl discussionService;
    @Autowired
    AccountInfoServiceImpl accountInfoService;
    @Autowired
    PermissionServiceImpl permissionService;
    @ApiOperation("发布一条讨论")
    @PostMapping("/insertDiscussion")
    public RespBean insertDiscussion(@RequestBody Discussion discussion) {
        Long id = discussion.getUId();
        if(permissionService.getById(id).getDiscussion()==0){
            return RespBean.error("你已被禁止发起讨论");
        }
        discussion.setTime(new Date());
        if (discussionService.save(discussion))
            return RespBean.ok("讨论发布成功");
        return RespBean.error("发布失败，请稍后重试");
    }

    @ApiOperation("传入id，删除此条讨论")
    @GetMapping("/delete")
    public RespBean delete(Long id) {
        if (discussionService.removeById(id))
            return RespBean.ok("讨论删除成功");
        return RespBean.error("删除失败，请稍后重试");
    }

    @ApiOperation("传入key，查询所有包含该关键字的讨论")
    @GetMapping("/getAllByKey")
    public RespBean getAllByKey(String key) {
        return RespBean.ok("获取成功",discussionService.getAllByKey(key));
    }

    @ApiOperation("获取某个讨论区的所有讨论，需要传入teaching_id")
    @GetMapping("/getDiscussionByTid")
    public RespBean getDiscussionByTid(Long tid) {
        List<Discussion> discussions = discussionService.getDiscussionByTid(tid);
        System.out.println(discussions);
        List<Object> list=new LinkedList<>();
        for(Discussion discussion:discussions){
            HashMap<String, Object> map = new HashMap<>();
            map.put("discussion",discussion);
            map.put("userInfo",accountInfoService.getUserBaseInfoById(discussion.getUId()));
            list.add(map);
        }
//        List<Map<String, Object>> list = discussions.stream().map(item -> accountInfoService.getUserBaseInfoById(item.getUId())).collect(Collectors.toList());
        return RespBean.ok("获取成功",list);
    }
}

