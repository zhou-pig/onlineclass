package com.graduation.onlineclass.service.impl;

import com.graduation.onlineclass.entity.AccountInfo;
import com.graduation.onlineclass.entity.Teaching;
import com.graduation.onlineclass.mapper.AccountInfoMapper;
import com.graduation.onlineclass.mapper.CourseMapper;
import com.graduation.onlineclass.mapper.TeachingMapper;
import com.graduation.onlineclass.mapper.TeachingMemberMapper;
import com.graduation.onlineclass.service.TeachingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    @Autowired
    CourseMapper courseMapper;
    @Autowired
    AccountInfoMapper accountInfoMapper;
    @Autowired
    TeachingMemberMapper teachingMemberMapper;
    public List<Teaching> getCourseListByuId(Long uId) {
        Map<String,Object> map=new HashMap<>();
        map.put("u_id",uId);
        return teachingMapper.selectByMap(map);
    }

    public Long getTeachingId(Long tid, Long cid) {
        return teachingMapper.getTeachingId(tid,cid);
    }

    public List<Object> getTeachingByKey(String key) {
        ArrayList<Object> list = new ArrayList<>();
        List<Teaching> teachings = teachingMapper.getTeachingByKey(key);
        for(Teaching t:teachings){
            HashMap<String, Object> map = new HashMap<>();
            map.put("course",courseMapper.selectById(t.getCId()));
            map.put("teacher",accountInfoMapper.selectById(t.getUId()));
            map.put("student",teachingMemberMapper.selectByTId(t.getTeachingId()));
            map.put("teachingId",t.getTeachingId());
            list.add(map);
        }
        return list;
    }
    public Object getTeachingById(Long tid) {
        Teaching t =teachingMapper.selectById(tid);
        HashMap<String, Object> map = new HashMap<>();
        map.put("course",courseMapper.selectById(t.getCId()));
        map.put("teacher",accountInfoMapper.selectById(t.getUId()));
        map.put("student",teachingMemberMapper.selectByTId(t.getTeachingId()));
        map.put("teachingId",t.getTeachingId());
        return map;
    }

}
