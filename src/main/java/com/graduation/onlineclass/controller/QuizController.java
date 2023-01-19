package com.graduation.onlineclass.controller;


import com.graduation.onlineclass.entity.Quiz;
import com.graduation.onlineclass.entity.RespBean;
import com.graduation.onlineclass.service.impl.QuizServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 周富雄
 * @since 2023-01-16
 */
@RestController
@Api(tags = "课堂测试模块")
@RequestMapping("/quiz")
public class QuizController {
    @Autowired
    QuizServiceImpl quizService;

    @ApiOperation("插入一个题目")
    @PostMapping("/insertQuiz")
    public RespBean insertQuiz(@RequestBody Quiz quiz) {
        System.out.println(quiz);
        if (quizService.save(quiz)) {
            return RespBean.ok("题目发布成功");
        }
        return RespBean.error("题目发布失败，请重试");
    }
}

