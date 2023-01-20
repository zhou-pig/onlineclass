package com.graduation.onlineclass.controller;


import com.graduation.onlineclass.entity.AccountInfo;
import com.graduation.onlineclass.entity.RespBean;
import com.graduation.onlineclass.entity.Teaching;
import com.graduation.onlineclass.entity.TeachingMember;
import com.graduation.onlineclass.service.impl.AccountInfoServiceImpl;
import com.graduation.onlineclass.service.impl.CourseServiceImpl;
import com.graduation.onlineclass.service.impl.TeachingMemberServiceImpl;
import com.graduation.onlineclass.service.impl.TeachingServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 周富雄
 * @since 2023-01-19
 */
@RestController
@Api(tags = "课堂成员信息模块")
@RequestMapping("/teachingMember")
public class TeachingMemberController {
    @Autowired
    TeachingMemberServiceImpl teachingMemberService;
    @Autowired
    TeachingServiceImpl teachingService;
    @Autowired
    CourseServiceImpl courseService;
    @Autowired
    AccountInfoServiceImpl accountInfoService;

    @ApiOperation("传入用户id，获取所上课堂信息")
    @GetMapping("/getCourseList")
    public RespBean getCourseList(Long uId) {
        List<TeachingMember> courseList = teachingMemberService.getCourseListByuId(uId);
        List<Object> list = new LinkedList<>();
        //遍历自己上的每一个课
        for (TeachingMember i : courseList) {
            Map<String, Object> map = new HashMap<>();
            //获取课程信息
            //通过teachingId获取teaching记录，这样就拿到了课程号c_id，再在课程表中查找
            Teaching teaching = teachingService.getById(i.getTeachingId());
            map.put("course", courseService.getById(teaching.getCId()));
            //下面获取教师信息
            map.put("teacher", accountInfoService.getUserBaseInfoById(teaching.getUId()));

            //下面获取该课堂的学生信息
            List<Object> students = new LinkedList<>();
            List<TeachingMember> studentList = teachingMemberService.getStudentsByteachingId(teaching.getTeachingId());
            for (TeachingMember t : studentList) {
                students.add(accountInfoService.getUserBaseInfoById(t.getUId()));
            }
            map.put("student", students);
            list.add(map);
        }
        return RespBean.ok("获取成功", list);
    }
}

