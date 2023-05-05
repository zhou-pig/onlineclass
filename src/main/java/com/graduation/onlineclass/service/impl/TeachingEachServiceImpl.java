package com.graduation.onlineclass.service.impl;

import com.graduation.onlineclass.entity.Quiz;
import com.graduation.onlineclass.entity.TeachingEach;
import com.graduation.onlineclass.mapper.TeachingEachMapper;
import com.graduation.onlineclass.mapper.TeachingMapper;
import com.graduation.onlineclass.service.TeachingEachService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 周富雄
 * @since 2023-05-01
 */
@Service
public class TeachingEachServiceImpl extends ServiceImpl<TeachingEachMapper, TeachingEach> implements TeachingEachService {

    @Autowired
    TeachingEachMapper teachingEachMapper;
    @Autowired
    TeachingMemberServiceImpl teachingMemberService;
    @Autowired
    QuizServiceImpl quizService;
    @Autowired
    QuizSubmitServiceImpl quizSubmitService;
    public List<TeachingEach> getByTid(Long tid) {
        return teachingEachMapper.getByTid(tid);
    }

    public List<Map<String, Object>> getScore(Long teachingEachId) {
        //存储结果
        ArrayList<Map<String, Object>> ret = new ArrayList<>();
        //先找到对应的课堂
        Long teachingId = teachingEachMapper.selectById(teachingEachId).getTeachingId();
        //找到课堂对应的学生
        List<Map<String, Object>> student = teachingMemberService.getStudent(teachingId);
        //找到当前课堂的所有题目
        List<Quiz> quizList = quizService.getQuizByTid(teachingEachId);
        //遍历每一个学生，获取其成绩
        for(Map<String,Object> m:student){
            HashMap<String, Object> map = new HashMap<>();
            ArrayList<Map<String, Object>> questions = new ArrayList<>();
            map.put("student",m);
            //获得学生的id
            Long uid = (Long)m.get("u_id");
            //记录总分
            int totalScore = 0;
            //遍历一个学生的所有题目集
            for(Quiz quiz:quizList){
                HashMap<String, Object> q = new HashMap<>();//用来存一个题的答题情况
                System.out.println("qid:"+quiz.getQId()+",uid="+uid);
                Map<String, Object> t = quizSubmitService.getById(quiz.getQId(), uid);
                System.out.println("t============");
                System.out.println(t);
                q.put("qid",quiz.getQId());
                //要保证有提交记录，写答案正确
                if(t!=null&&(Integer)t.get("is_right")==1){
                    int s = (Integer)t.get("score");
                    q.put("score",s);
                    totalScore += s;
                }else{
                    q.put("score",0);
                }
                questions.add(q);//加入
            }
            map.put("questions",questions);
            map.put("totalScore",totalScore);
            ret.add(map);
        }
        return ret;
    }
    public List<List<Object>> getScoreSimple(Long teachingEachId) {
        //存储结果
        ArrayList<List<Object>> ret = new ArrayList<>();
        //先找到对应的课堂
        Long teachingId = teachingEachMapper.selectById(teachingEachId).getTeachingId();
        //找到课堂对应的学生
        List<Map<String, Object>> student = teachingMemberService.getStudent(teachingId);
        //找到当前课堂的所有题目
        List<Quiz> quizList = quizService.getQuizByTid(teachingEachId);
        //遍历每一个学生，获取其成绩
        for(Map<String,Object> m:student){
            ArrayList<Object> list = new ArrayList<>();
            list.add(m.get("real_name"));
            //获得学生的id
            Long uid = (Long)m.get("u_id");
            //记录总分
            int totalScore = 0;
            //遍历一个学生的所有题目集
            for(Quiz quiz:quizList){
                Map<String, Object> t = quizSubmitService.getById(quiz.getQId(), uid);
                //要保证有提交记录，写答案正确
                if(t!=null&&(Integer)t.get("is_right")==1){
                    int s = (Integer)t.get("score");
                    list.add(s);
                    totalScore += s;
                }else{
                    list.add(0);
                }
            }
            list.add(totalScore);
            ret.add(list);
        }
        return ret;
    }
}
