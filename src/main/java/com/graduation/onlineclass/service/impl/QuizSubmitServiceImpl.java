package com.graduation.onlineclass.service.impl;

import com.graduation.onlineclass.entity.QuizSubmit;
import com.graduation.onlineclass.mapper.QuizSubmitMapper;
import com.graduation.onlineclass.service.QuizSubmitService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class QuizSubmitServiceImpl extends ServiceImpl<QuizSubmitMapper, QuizSubmit> implements QuizSubmitService {
    @Autowired
    QuizSubmitMapper quizSubmitMapper;
    public List<Map<String,Object>> getByQid(Long qid) {
        return quizSubmitMapper.getByQid(qid);
    }

    public List<Map<String, Object>> getRightByQid(Long qid) {
        return quizSubmitMapper.getRightByQid(qid);
    }

    public List<Map<String, Object>> getUndoStudent(Long teachingId, Long qid) {
        return quizSubmitMapper.getUndoStudent(teachingId,qid);
    }

    public Map<String,Object> getById(Long qid, Long uid) {
        return quizSubmitMapper.getById(qid,uid);
    }
}
