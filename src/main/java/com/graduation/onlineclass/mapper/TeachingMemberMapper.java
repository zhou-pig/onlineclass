package com.graduation.onlineclass.mapper;

import com.graduation.onlineclass.entity.AccountInfo;
import com.graduation.onlineclass.entity.TeachingMember;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 周富雄
 * @since 2023-01-19
 */
@Mapper
public interface TeachingMemberMapper extends BaseMapper<TeachingMember> {

    List<AccountInfo> selectByTId(Long teachingId);

    int deleteUserFromTeaching(Long tid, Long uid);
}
