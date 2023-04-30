package com.graduation.onlineclass.mapper;

import com.graduation.onlineclass.entity.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 周富雄
 * @since 2023-01-16
 */
@Mapper
public interface CourseMapper extends BaseMapper<Course> {

    Long myInsert(Long cId, String courseName, String courseBasicInfo, Integer credit);
}
