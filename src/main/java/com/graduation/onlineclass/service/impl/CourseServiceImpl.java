package com.graduation.onlineclass.service.impl;

import com.graduation.onlineclass.entity.Course;
import com.graduation.onlineclass.mapper.CourseMapper;
import com.graduation.onlineclass.service.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 周富雄
 * @since 2023-01-16
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Autowired
    CourseMapper courseMapper;
    public Long myInsert(Course course) {
        return courseMapper.myInsert(course.getCId(),course.getCourseName(),course.getCourseBasicInfo(),course.getCredit());
    }
}
