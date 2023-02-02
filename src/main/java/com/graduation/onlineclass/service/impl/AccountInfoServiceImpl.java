package com.graduation.onlineclass.service.impl;

import com.graduation.onlineclass.entity.AccountInfo;
import com.graduation.onlineclass.entity.UserInfo;
import com.graduation.onlineclass.mapper.AccountInfoMapper;
import com.graduation.onlineclass.service.AccountInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 周富雄
 * @since 2023-01-20
 */
@Service
public class AccountInfoServiceImpl extends ServiceImpl<AccountInfoMapper, AccountInfo> implements AccountInfoService {

    @Autowired
    AccountInfoMapper accountInfoMapper;

    public List<AccountInfo> getAccountInfoByMap(Map<String, Object> map) {
        return accountInfoMapper.selectByMap(map);
    }

    //通过uId，获取用户基本信息
    public Map<String, Object> getUserBaseInfoById(Long uId) {
        System.out.println("getUserBaseInfoById:"+uId);
        AccountInfo student = accountInfoMapper.selectById(uId);
        System.out.println(student);
        Map<String, Object> studentBaseInfo = new HashMap<>();
        studentBaseInfo.put("name", student.getRealName());
        studentBaseInfo.put("university", student.getUniversity());
        studentBaseInfo.put("uId", student.getId());
        return studentBaseInfo;
    }

}
