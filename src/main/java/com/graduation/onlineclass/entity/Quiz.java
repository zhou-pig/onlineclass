package com.graduation.onlineclass.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author 周富雄
 * @since 2023-01-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Quiz对象", description="")
public class Quiz implements Serializable {

    private static final long serialVersionUID=1L;

    private Long qId;

    @ApiModelProperty(value = "选择，判断")
    private String type;

    @ApiModelProperty(value = "题目")
    private String question;

    @ApiModelProperty(value = "对于判断，正确为：true，错误为：false。对于选择题，单选就是一个字符，多选如：A,B,C")
    private String answer;

    private Date startTime;

    @ApiModelProperty(value = "单位：秒")
    private Integer duration;

    private Date endTime;

    @ApiModelProperty(value = "0假1真")
    @TableField("isEnded")
    private Integer isEnded;

    @ApiModelProperty(value = "发布人id")
    private Long authorId;

    @ApiModelProperty(value = "题目分数")
    private Integer score;


}
