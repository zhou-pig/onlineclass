package com.graduation.onlineclass.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
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
@ApiModel(value="Quiz对象", description="")
public class Quiz implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "q_id", type = IdType.AUTO)
    private Long qId;

    @ApiModelProperty(value = "选择，判断")
    private String type;

    @ApiModelProperty(value = "题目")
    private String question;

    @ApiModelProperty(value = "对于判断，正确为：true，错误为：false。对于选择题，单选就是一个字符，多选如：A,B,C")
    private String answer;

    @ApiModelProperty(value = "题目开始时间")
    private Date startTime;

    @ApiModelProperty(value = "答题时间，单位：秒")
    private Integer duration;

    @ApiModelProperty(value = "题目结束时间")
    private Date endTime;

    @ApiModelProperty(value = "0假1真")
    @TableField("isEnded")
    private Integer isended;

    @ApiModelProperty(value = "发布人id")
    private Long authorId;

    @ApiModelProperty(value = "题目分数")
    private Integer score;


}
