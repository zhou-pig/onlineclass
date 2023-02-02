package com.graduation.onlineclass.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

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
 * @since 2023-02-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Discussion对象", description="")
public class Discussion implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "讨论区所在的课程")
    private Long teachingId;

    @ApiModelProperty(value = "讨论")
    @TableId(value = "discussion_id", type = IdType.AUTO)
    private Long discussionId;

    @ApiModelProperty(value = "讨论的内容")
    private String content;

    @ApiModelProperty(value = "发布者id")
    private Long uId;

    @ApiModelProperty(value = "发布时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")//后端注解格式化日期
    private Date time;


}
