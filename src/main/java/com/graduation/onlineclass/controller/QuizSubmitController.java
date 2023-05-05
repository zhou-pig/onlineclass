package com.graduation.onlineclass.controller;


import com.graduation.onlineclass.entity.QuizSubmit;
import com.graduation.onlineclass.entity.RespBean;
import com.graduation.onlineclass.service.impl.QuizSubmitServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 周富雄
 * @since 2023-01-16
 */
@RestController
@Api(tags = "课堂测试作答记录模块")
@RequestMapping("/quizSubmit")
public class QuizSubmitController {
    @Autowired
    QuizSubmitServiceImpl quizSubmitService;

    @ApiOperation("传入q_id，u_id，获取该生该题目的作答情况")
    @GetMapping("/getResultById")
    public RespBean getById(Long qid, Long uid){

        return RespBean.ok("获取成功", quizSubmitService.getById(qid,uid));
    }

    @ApiOperation("插入一个quiz_submit对象")
    @PostMapping("/insert")
    public RespBean insert(@RequestBody QuizSubmit quizSubmit){
        if(quizSubmitService.save(quizSubmit)){
            return RespBean.ok("提交成功");
        }
        return RespBean.error("提交失败");
    }
}

