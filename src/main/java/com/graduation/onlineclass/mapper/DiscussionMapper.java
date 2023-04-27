package com.graduation.onlineclass.mapper;

import com.graduation.onlineclass.entity.Discussion;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 周富雄
 * @since 2023-02-02
 */
@Mapper
public interface DiscussionMapper extends BaseMapper<Discussion> {

    List<Discussion> getDiscussionByTid(Long tid);

    List<Discussion> getByKey(String key);
}
