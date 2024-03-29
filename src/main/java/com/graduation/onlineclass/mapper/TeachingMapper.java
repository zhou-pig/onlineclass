package com.graduation.onlineclass.mapper;

import com.graduation.onlineclass.entity.Teaching;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 周富雄
 * @since 2023-01-16
 */
@Mapper
public interface TeachingMapper extends BaseMapper<Teaching> {

    Long getTeachingId(Long tid, Long cid);

    List<Teaching> getTeachingByKey(String key);
}
