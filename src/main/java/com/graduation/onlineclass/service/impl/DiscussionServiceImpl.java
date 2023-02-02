package com.graduation.onlineclass.service.impl;

import com.graduation.onlineclass.entity.Discussion;
import com.graduation.onlineclass.mapper.DiscussionMapper;
import com.graduation.onlineclass.service.DiscussionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 周富雄
 * @since 2023-02-02
 */
@Service
public class DiscussionServiceImpl extends ServiceImpl<DiscussionMapper, Discussion> implements DiscussionService {

    @Autowired
    DiscussionMapper discussionMapper;

    public List<Discussion> getDiscussionByTid(Long tid) {
        return discussionMapper.getDiscussionByTid(tid);
    }
}
