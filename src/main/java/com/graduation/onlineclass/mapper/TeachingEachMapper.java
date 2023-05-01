package com.graduation.onlineclass.mapper;

import com.graduation.onlineclass.entity.TeachingEach;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 周富雄
 * @since 2023-05-01
 */
@Mapper
public interface TeachingEachMapper extends BaseMapper<TeachingEach> {

    List<TeachingEach> getByTid(Long tid);
}
