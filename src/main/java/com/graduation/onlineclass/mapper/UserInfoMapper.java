package com.graduation.onlineclass.mapper;

import com.graduation.onlineclass.entity.UserInfo;
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
public interface UserInfoMapper extends BaseMapper<UserInfo> {

    int setWxCode(Long uId,String wxCode);
}
