package com.graduation.onlineclass.service.impl;

import com.graduation.onlineclass.entity.AccountInfo;
import com.graduation.onlineclass.mapper.AccountInfoMapper;
import com.graduation.onlineclass.service.AccountInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 周富雄
 * @since 2023-01-16
 */
@Service
public class AccountInfoServiceImpl extends ServiceImpl<AccountInfoMapper, AccountInfo> implements AccountInfoService {

    @Autowired
    AccountInfoMapper accountInfoMapper;
    private String realName;

    public boolean isExistent(Map<String, Object> map) {
        System.out.println(map);
//        String uId= (String) map.get("uId");
//        String password = (String) map.get("password");
//        String university= (String) map.get("university");
//        String role=(String) map.get("role");
//        String realName= (String) map.get("realName");
//        if(map.get("wxCode")!=null)
//            map.remove("wxCode");
        return accountInfoMapper.selectByMap(map).size()>0;

    }
}
