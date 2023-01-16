package com.graduation.onlineclass.entity;

import java.util.Date;
import java.io.Serializable;
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

    @ApiModelProperty(value = "聊天信息的id")
    private Long chatId;

    @ApiModelProperty(value = "发送者的id")
    private String sender;

    @ApiModelProperty(value = "接收者的id")
    private String receiver;

    @ApiModelProperty(value = "发送时间")
    private Date sendTime;

    @ApiModelProperty(value = "0假1真")
    private Integer isRead;

    @ApiModelProperty(value = "内容")
    private String content;


}
