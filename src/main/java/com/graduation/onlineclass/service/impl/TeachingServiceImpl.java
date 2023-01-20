package com.graduation.onlineclass.service.impl;

import com.graduation.onlineclass.entity.Teaching;
import com.graduation.onlineclass.mapper.TeachingMapper;
import com.graduation.onlineclass.service.TeachingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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
public class TeachingServiceImpl extends ServiceImpl<TeachingMapper, Teaching> implements TeachingService {

    @Autowired
    TeachingMapper teachingMapper;

    public List<Teaching> getCourseListByuId(Long uId) {
        Map<String,Object> map=new HashMap<>();
        map.put("u_id",uId);
        return teachingMapper.selectByMap(map);
    }
}
