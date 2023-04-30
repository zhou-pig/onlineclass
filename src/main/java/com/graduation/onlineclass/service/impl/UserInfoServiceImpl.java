package com.graduation.onlineclass.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.graduation.onlineclass.entity.UserInfo;
import com.graduation.onlineclass.mapper.UserInfoMapper;
import com.graduation.onlineclass.service.UserInfoService;
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
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {
    @Autowired
    public UserInfoMapper userInfoMapper;
    public int login(String wxCode){
        return userInfoMapper.insert(new UserInfo(wxCode));
    }

    public UserInfo getByWxCode(String wxCode) {
        QueryWrapper<UserInfo>  queryWrapper =new QueryWrapper<>();
        queryWrapper.eq("wx_code",wxCode);
        return userInfoMapper.selectOne(queryWrapper);
    }

    public int setWxCode(Long uId,String wxCode) {
        return userInfoMapper.setWxCode(uId,wxCode);
    }
}
