package com.graduation.onlineclass.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 *
 * </p>
 *
 * @author 周富雄
 * @since 2023-05-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="TeachingEach对象", description="")
@NoArgsConstructor
@AllArgsConstructor
public class TeachingEach implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "每一次授课的id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "老师所授课的id")
    private Long teachingId;

    @ApiModelProperty(value = "该次授课的标题")
    private String title;

    @ApiModelProperty(value = "该次授课的简介")
    private String info;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")//后端注解格式化日期
    @ApiModelProperty(value = "课堂开始时间")
    private Date startTime;


}
