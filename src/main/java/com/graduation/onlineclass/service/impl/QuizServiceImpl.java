package com.graduation.onlineclass.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.graduation.onlineclass.entity.Quiz;
import com.graduation.onlineclass.entity.QuizSubmit;
import com.graduation.onlineclass.entity.Teaching;
import com.graduation.onlineclass.entity.TeachingEach;
import com.graduation.onlineclass.mapper.QuizMapper;
import com.graduation.onlineclass.mapper.TeachingEachMapper;
import com.graduation.onlineclass.mapper.TeachingMapper;
import com.graduation.onlineclass.service.QuizService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 周富雄
 * @since 2023-01-16
 */
@Service
public class QuizServiceImpl extends ServiceImpl<QuizMapper, Quiz> implements QuizService {

    @Autowired
    QuizMapper quizMapper;
    @Autowired
    TeachingEachMapper teachingEachMapper;
    @Autowired
    TeachingServiceImpl teachingService;
    @Autowired
    QuizSubmitServiceImpl quizSubmitService;

    public List<Quiz> getQuizByTid(Long id) {
        QueryWrapper<Quiz> quizQueryWrapper=new QueryWrapper<>();
        quizQueryWrapper.eq("teaching_each_id",id);
        return quizMapper.selectList(quizQueryWrapper);
    }

    public List<Object> getMyQuiz(Long tid, Long uid) {
        return quizMapper.getMyQuiz(tid,uid);
    }

    public Map<String,Object> getQuizResult(Long qid) {
        Map<String,Object> map = new HashMap<String,Object>();
        //先获取该题目对应的学生人数
        Quiz quiz = quizMapper.selectById(qid);
        TeachingEach teachingEach = teachingEachMapper.selectById(quiz.getTeachingEachId());
        Teaching teaching = teachingService.getById(teachingEach.getTeachingId());
        map.put("student",teachingService.getTeachingById(teaching.getTeachingId()).get("student"));
        //获取作答情况
        List<Map<String,Object>> list = quizSubmitService.getByQid(qid);
        map.put("submitted",list);
        //获取正确作答的情况
        List<Map<String,Object>> right = quizSubmitService.getRightByQid(qid);
        map.put("rightSubmitted",right);
        return map;
    }
}
