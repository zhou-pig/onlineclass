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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

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

    @ApiOperation("传入用户id，获取所学课堂信息")
    @GetMapping("/getCourseList0")
    public RespBean getCourseList0(Long uId) {
        //返回结果
        List<Object> list = new LinkedList<>();
        List<TeachingMember> courseList = teachingMemberService.getCourseListByuId(uId);
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
            //下面获取teaching_id
            map.put("teachingId", i.getTeachingId());
            list.add(map);
        }
        return RespBean.ok("获取成功", list);
    }

    @ApiOperation("传入用户id，获取所教课堂信息")
    @GetMapping("/getCourseList1")
    public RespBean getCourseList1(Long uId) {
        //返回结果
        List<Object> list = new LinkedList<>();
        //先获取到老师上的全部课程
        List<Teaching> teachingList = teachingService.getCourseListByuId(uId);
        //遍历每一个课程
        for (Teaching t : teachingList) {
            Map<String, Object> map = new HashMap<>();
            map.put("course", courseService.getById(t.getCId()));
            map.put("teacher", accountInfoService.getUserBaseInfoById(uId));
            //下面获取该课堂的学生信息
            List<Object> students = new LinkedList<>();
            List<TeachingMember> studentList = teachingMemberService.getStudentsByteachingId(t.getTeachingId());
            for (TeachingMember s : studentList) {
                students.add(accountInfoService.getUserBaseInfoById(s.getUId()));
            }
            map.put("student", students);
            //下面获取teaching_id
            map.put("teachingId", t.getTeachingId());
            list.add(map);
        }
        return RespBean.ok("获取成功", list);
    }

    @ApiOperation("传入teachingId和uId,删除该用户在此课堂的信息")
    @GetMapping("/deleteUserFromTeaching")
    public RespBean deleteUserFromTeaching(Long tid,Long uid){
        return RespBean.ok("删除成功",teachingMemberService.deleteUserFromTeaching(tid,uid));
    }

    @ApiOperation("传入teachingId,获取这个课堂的学生信息")
    @GetMapping("/getStudent")
    public RespBean getStudent(Long tid){
        return RespBean.ok("获取成功",teachingMemberService.getStudent(tid));
    }

    @PostMapping("/addStudentByFile")
    @ApiOperation("通过文件录入用户信息")
    public RespBean saveFile(@RequestParam("file") MultipartFile file,@RequestParam("tid") Long tid) {

        System.out.println("tid:"+tid);
        try {
            // 获取上传的文件名
            String fileName = file.getOriginalFilename();
            // 获取文件输入流
            InputStream inputStream = file.getInputStream();
            // 读取文件内容
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;
            ArrayList<String> failList = new ArrayList<>();
            ArrayList<String> successList = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                // 处理每一行的文件内容，比如输出到控制台
                System.out.println(line);
                String[] split = line.split(" ");
                String university = split[0];
                String account = split[1];
                Long uid = accountInfoService.getAccount(account,university).getId();
                if (!teachingMemberService.isExisted(uid,tid) && teachingMemberService.save(new TeachingMember(uid,tid,null))) {//录入成功
                    successList.add(line);
                } else {
                    failList.add(line);
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

