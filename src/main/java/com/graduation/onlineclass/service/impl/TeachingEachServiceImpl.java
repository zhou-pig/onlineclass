package com.graduation.onlineclass.service.impl;

import com.graduation.onlineclass.entity.TeachingEach;
import com.graduation.onlineclass.mapper.TeachingEachMapper;
import com.graduation.onlineclass.service.TeachingEachService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 周富雄
 * @since 2023-05-01
 */
@Service
public class TeachingEachServiceImpl extends ServiceImpl<TeachingEachMapper, TeachingEach> implements TeachingEachService {

    @Autowired
    TeachingEachMapper teachingEachMapper;

    public List<TeachingEach> getByTid(Long tid) {
        return teachingEachMapper.getByTid(tid);
    }
}
