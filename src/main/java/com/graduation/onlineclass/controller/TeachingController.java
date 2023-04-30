package com.graduation.onlineclass.controller;


import com.graduation.onlineclass.entity.AccountInfo;
import com.graduation.onlineclass.entity.Course;
import com.graduation.onlineclass.entity.RespBean;
import com.graduation.onlineclass.entity.Teaching;
import com.graduation.onlineclass.service.impl.AccountInfoServiceImpl;
import com.graduation.onlineclass.service.impl.CourseServiceImpl;
import com.graduation.onlineclass.service.impl.TeachingServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/teaching")
@Api(tags = "老师授课信息模块")
public class TeachingController {
    @Autowired
    TeachingServiceImpl teachingService;
    @Autowired
    CourseServiceImpl courseService;
    @Autowired
    AccountInfoServiceImpl accountInfoService;

    @ApiOperation("传入老师号，获取所有授课信息")
    @GetMapping("/getCourseList")
    public RespBean getCourseList(Long uId) {
        return RespBean.ok("获取成功", teachingService.getCourseListByuId(uId));
    }

    @ApiOperation("传入teaching_id，获取对应课程信息")
    @GetMapping("/getTeaching")
    public RespBean getTeaching(Long tid) {
        return RespBean.ok("获取成功", teachingService.getTeachingById(tid));
    }

    @ApiOperation("传入老师和课程id，获取对应teaching_id")
    @GetMapping("/getTeachingId")
    public RespBean getTeaching(Long tid, Long cid) {
        return RespBean.ok("获取成功", teachingService.getTeachingId(tid, cid));
    }

    @ApiOperation("通过老师姓名和课堂的关键字，查询满足条件的授课信息")
    @GetMapping("/getTeachingByKey")
    public RespBean getTeachingByKey(String key) {
        return RespBean.ok("获取成功", teachingService.getTeachingByKey(key));
    }

    @ApiOperation("传入老师学校，account和课程信息，新增一个课堂信息")
    @PostMapping("/add")
    public RespBean getTeachingByKey(@RequestBody Map<String, Object> map) {
        System.out.println("/add ");
        String account = (String) map.get("account");
        String university = (String) map.get("university");
        AccountInfo teacher = accountInfoService.getAccount(account, university);
        if(teacher == null)
            return RespBean.error("无该老师信息！");
        String courseName = (String) map.get("courseName");
        Integer credit = Integer.valueOf((String) map.get("credit"));
        String basicInfo = (String) map.get("basicInfo");
        Course course = new Course(null, courseName, basicInfo, credit);
        courseService.save(course);
        Long cid = course.getCId();
        if (teachingService.save(new Teaching(cid, teacher.getId(), null)))
            return RespBean.ok("新增成功");
        else
            return RespBean.error("新增失败");
    }
}

