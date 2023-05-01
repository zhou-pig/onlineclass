package com.graduation.onlineclass.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * @since 2023-01-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Quiz对象", description="")
public class Quiz implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "q_id", type = IdType.AUTO)
    private Long qId;

    @ApiModelProperty(value = "选择，判断")
    private String type;

    @ApiModelProperty(value = "题目，判断直接题干。选择，格式为：题目;选项1;选项2;选项3;选项4")
    private String question;

    @ApiModelProperty(value = "对于判断，正确为：1，错误为：0。对于选择题，单选就是一个选项序号，多选如：0,1,2")
    private String answer;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")//后端注解格式化日期
    @ApiModelProperty(value = "题目开始时间")
    private Date startTime;

    @ApiModelProperty(value = "答题时间，单位：秒")
    private Integer duration;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")//后端注解格式化日期
    @ApiModelProperty(value = "题目结束时间")
    private Date endTime;

    @ApiModelProperty(value = "0假1真")
    @TableField("isEnded")
    private Integer isended;

    @ApiModelProperty(value = "题目所在课堂id")
    private Long teachingEachId;

    @ApiModelProperty(value = "题目分数")
    private Integer score;


}
