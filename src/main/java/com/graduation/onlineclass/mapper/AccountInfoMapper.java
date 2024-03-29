package com.graduation.onlineclass.mapper;

import com.graduation.onlineclass.entity.AccountInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 周富雄
 * @since 2023-01-20
 */
@Mapper
public interface AccountInfoMapper extends BaseMapper<AccountInfo> {
    String getRealNameById(Long id);

    List<Long> getAllId();

    List<Long> getAllIdByKey(String key);

    AccountInfo getAccount(String account, String university);
}
