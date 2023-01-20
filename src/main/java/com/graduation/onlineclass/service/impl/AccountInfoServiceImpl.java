package com.graduation.onlineclass.service.impl;

import com.graduation.onlineclass.entity.AccountInfo;
import com.graduation.onlineclass.mapper.AccountInfoMapper;
import com.graduation.onlineclass.service.AccountInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 周富雄
 * @since 2023-01-20
 */
@Service
public class AccountInfoServiceImpl extends ServiceImpl<AccountInfoMapper, AccountInfo> implements AccountInfoService {

    @Autowired
    AccountInfoMapper accountInfoMapper;
    public boolean isExistent(Map<String, Object> map) {
        return accountInfoMapper.selectByMap(map).size()>0;
    }
}
