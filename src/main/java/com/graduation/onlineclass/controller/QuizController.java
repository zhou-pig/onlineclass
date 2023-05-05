package com.graduation.onlineclass.controller;


import com.graduation.onlineclass.entity.Quiz;
import com.graduation.onlineclass.entity.QuizSubmit;
import com.graduation.onlineclass.entity.RespBean;
import com.graduation.onlineclass.service.QuizService;
import com.graduation.onlineclass.service.impl.QuizServiceImpl;
import com.graduation.onlineclass.service.impl.QuizSubmitServiceImpl;
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
 * @since 2023-01-16
 */
@RestController
@Api(tags = "课堂测试模块")
@RequestMapping("/quiz")
public class QuizController {
    @Autowired
    QuizServiceImpl quizService;
    @Autowired
    QuizSubmitServiceImpl quizSubmitService;

    @ApiOperation("插入一个题目")
    @PostMapping("/insertQuiz")
    public RespBean insertQuiz(@RequestBody Quiz quiz) {
        System.out.println(quiz);
        if (quizService.save(quiz)) {
            return RespBean.ok("题目发布成功");
        }
        return RespBean.error("题目发布失败，请重试");
    }

    @ApiOperation("传入teaching_id，获取所有题目")
    @GetMapping("/getQuizByTid")
    public RespBean getQuiz(Long id){
        return RespBean.ok("获取成功",quizService.getQuizByTid(id));
    }

    @ApiOperation("传入q_id，获取指定题目")
    @GetMapping("/getQuizByQid")
    public RespBean getQuizByQid(Long id){
        return RespBean.ok("获取成功",quizService.getById(id));
    }

    @ApiOperation("传入t_id，u_id，获取对应题目以及作答情况")
    @GetMapping("/getMyQuiz")
    public RespBean getMyQuiz(Long tid,Long uid){
        return RespBean.ok("获取成功",quizService.getMyQuiz(tid,uid));
    }

    @ApiOperation("传入q_id，获取该题目的作答情况")
    @GetMapping("/getQuizResult")
    public RespBean getQuizResult(Long qid){

        return RespBean.ok("获取成功",quizService.getQuizResult(qid));
    }


    @ApiOperation("提交一个题目")
    @PostMapping("/submitQuiz")
    public RespBean insertQuizSubmit(@RequestBody QuizSubmit quizSubmit) {
        System.out.println(quizSubmit);
        if (quizSubmitService.save(quizSubmit)) {
            return RespBean.ok("题目提交成功");
        }
        return RespBean.error("题目提交失败，请重试");
    }
}
