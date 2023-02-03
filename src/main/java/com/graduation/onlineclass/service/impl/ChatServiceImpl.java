package com.graduation.onlineclass.service.impl;

import com.graduation.onlineclass.entity.Chat;
import com.graduation.onlineclass.mapper.AccountInfoMapper;
import com.graduation.onlineclass.mapper.ChatMapper;
import com.graduation.onlineclass.service.ChatService;
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
public class ChatServiceImpl extends ServiceImpl<ChatMapper, Chat> implements ChatService {

    @Autowired
    ChatMapper chatMapper;
    @Autowired
    AccountInfoMapper accountInfoMapper;
    public List<Object> getChatListById(Long id1) {
        List<Long> chatAllId = chatMapper.getChatAllId(id1);
        List<Object> chatList=new ArrayList<>();
        for(Long id2:chatAllId){
            List<Chat> list=chatMapper.getChatListByTwoId(id1,id2);
            Map<String, Object> map = new HashMap<>();
            map.put("name",accountInfoMapper.getRealNameById(id2));
            map.put("latestMsg",list.get(list.size()-1));
            map.put("chatList",list);
            chatList.add(map);
        }
        return  chatList;
    }
}
