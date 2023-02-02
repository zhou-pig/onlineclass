package com.graduation.onlineclass.controller;


import com.graduation.onlineclass.entity.Discussion;
import com.graduation.onlineclass.entity.RespBean;
import com.graduation.onlineclass.service.impl.DiscussionServiceImpl;
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
 * @since 2023-02-02
 */
@RestController
@RequestMapping("/discussion")
@Api(tags = "讨论区模块")
public class DiscussionController {

    @Autowired
    DiscussionServiceImpl discussionService;

    @ApiOperation("发布一条讨论")
    @PostMapping("/insertDiscussion")
    public RespBean insertDiscussion(@RequestBody Discussion discussion) {
        if (discussionService.save(discussion))
            return RespBean.ok("讨论发布成功");
        return RespBean.error("发布失败，请稍后重试");
    }

    @ApiOperation("获取某个讨论区的所有讨论，需要传入teaching_id")
    @GetMapping("/getDiscussionByTid")
    public RespBean getDiscussionByTid(Long tid) {
        return RespBean.ok("获取成功",discussionService.getDiscussionByTid(tid));
    }
}

