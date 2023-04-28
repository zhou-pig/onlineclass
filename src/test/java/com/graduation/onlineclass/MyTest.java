/**
 * -*- codeing = utf-8 -*-
 *
 * @Time :2023/1/19 16:28
 * @Author :zhou_pig
 * @File :MyTest.java
 * @Software :IntelliJ IDEA
 */
package com.graduation.onlineclass;

import com.graduation.onlineclass.mapper.QuizMapper;
import com.graduation.onlineclass.mapper.TeachingMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class MyTest {
    @Autowired
    QuizMapper quizMapper;

    @Autowired
    TeachingMapper teachingMapper;
    public static void main(String[] args) {

    }

    @Test
    public void test01() {
        System.out.println(new Date());
    }

    @Test
    public void test02() {
        if(quizMapper==null){
            System.out.println("quizMapper is null");
            return;
        }
        List<Object> list = quizMapper.getMyQuiz(1L, 3L);
        if (list == null)
            System.out.println("list is null");
        else
            System.out.println(list);

    }

    @Test
    public void test03(){
        if(teachingMapper == null){
            System.out.println("null");
        }
//        teachingMapper.getTeachingByKey("数据");
        teachingMapper.getTeachingId(1L,1L);
    }
}
