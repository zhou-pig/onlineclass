package com.graduation.onlineclass.service.impl;

import com.graduation.onlineclass.entity.Discussion;
import com.graduation.onlineclass.mapper.AccountInfoMapper;
import com.graduation.onlineclass.mapper.DiscussionMapper;
import com.graduation.onlineclass.service.DiscussionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
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
    @Autowired
    AccountInfoMapper accountInfoMapper;
    public List<Discussion> getDiscussionByTid(Long tid) {
        return discussionMapper.getDiscussionByTid(tid);
    }

    public List<Object> getAllByKey(String key) {
        ArrayList<Object> list = new ArrayList<>();
        List<Discussion> discussions = discussionMapper.getByKey(key);
        for(Discussion d:discussions){
            HashMap<String, Object> map = new HashMap<>();
//            map.put("dId",d.getDiscussionId());
//            map.put("time",d.getTime());
//            map.put("content",d.getContent());
//            map.put("tId",d.getTeachingId());
//            map.put("uId",d.getUId());
            map.put("discussion",d);
            map.put("name",accountInfoMapper.selectById(d.getUId()).getRealName());
            list.add(map);
        }
        return list;
    }
}
