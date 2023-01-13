package com.graduation.onlineclass.service.impl;

import com.graduation.onlineclass.entity.UserInfo;
import com.graduation.onlineclass.mapper.UserInfoMapper;
import com.graduation.onlineclass.services.UserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 周富雄
 * @since 2023-01-13
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

}
