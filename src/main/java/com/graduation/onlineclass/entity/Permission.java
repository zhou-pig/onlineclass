package com.graduation.onlineclass.entity;

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
 * @since 2023-04-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Permission对象", description="")
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @ApiModelProperty(value = "0：不能聊天  1：可以聊天")
    private Integer chat;

    @ApiModelProperty(value = "0：不能讨论  1：可以讨论")
    private Integer discussion;

    @ApiModelProperty(value = "0：未激活  1：激活")
    private Integer activated;


}
