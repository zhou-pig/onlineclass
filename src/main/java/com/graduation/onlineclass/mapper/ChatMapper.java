package com.graduation.onlineclass.mapper;

import com.graduation.onlineclass.entity.Chat;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 周富雄
 * @since 2023-01-16
 */
@Mapper
public interface ChatMapper extends BaseMapper<Chat> {

    List<Chat> getChatListByTwoId(Long id1,Long id2);
    List<Long> getChatAllId(Long id);
    //获取接收方未读数据条数
    Integer getUnreadNum(Long receiver,Long sender);
    //将消息设为已读
    Integer setAllIsRead(Long receiver,Long sender);
}
