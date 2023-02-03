package com.graduation.onlineclass.entity;

import java.util.Date;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author 周富雄
 * @since 2023-01-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Chat对象", description="")
public class Chat implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId( type = IdType.AUTO)
    @ApiModelProperty(value = "聊天信息的id")
    private Long chatId;

    @ApiModelProperty(value = "发送者的id")
    private Long sender;

    @ApiModelProperty(value = "接收者的id")
    private Long receiver;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")//后端注解格式化日期
    @ApiModelProperty(value = "发送时间")
    private Date sendTime;

    @ApiModelProperty(value = "0假1真")
    private Integer isRead;

    @ApiModelProperty(value = "内容")
    private String content;


}
