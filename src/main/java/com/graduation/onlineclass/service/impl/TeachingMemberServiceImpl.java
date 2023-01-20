package com.graduation.onlineclass.service.impl;

import com.graduation.onlineclass.entity.TeachingMember;
import com.graduation.onlineclass.mapper.TeachingMemberMapper;
import com.graduation.onlineclass.service.TeachingMemberService;
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
 * @since 2023-01-19
 */
@Service
public class TeachingMemberServiceImpl extends ServiceImpl<TeachingMemberMapper, TeachingMember> implements TeachingMemberService {
    @Autowired
    TeachingMemberMapper teachingMemberMapper;
    public List<TeachingMember> getCourseListByuId(Long uId) {
        Map<String,Object> map=new HashMap<>();
        map.put("u_id",uId);
        return teachingMemberMapper.selectByMap(map);
    }

    public List<TeachingMember> getStudentsByteachingId(Long teachingId) {
        HashMap<String,Object> map=new HashMap<>();
        map.put("teaching_id",teachingId);
        return teachingMemberMapper.selectByMap(map);
    }
}
