package com.graduation.onlineclass.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.graduation.onlineclass.entity.Quiz;
import com.graduation.onlineclass.mapper.QuizMapper;
import com.graduation.onlineclass.service.QuizService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Quiz> getQuizByTid(Long id) {
        QueryWrapper<Quiz> quizQueryWrapper=new QueryWrapper<>();
        quizQueryWrapper.eq("teaching_id",id);
        return quizMapper.selectList(quizQueryWrapper);
    }

    public List<Object> getMyQuiz(Long tid, Long uid) {
        return quizMapper.getMyQuiz(tid,uid);
    }
}
