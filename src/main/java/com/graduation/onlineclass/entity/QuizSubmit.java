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
 * @since 2023-01-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="QuizSubmit对象", description="")
public class QuizSubmit implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "随堂测试作答记录的id")
    @TableId(value = "qs_id", type = IdType.AUTO)
    private Long qsId;

    @ApiModelProperty(value = "题目id")
    private Long qId;

    @ApiModelProperty(value = "提交者的id")
    private Long uId;

    @ApiModelProperty(value = "提交的答案")
    private String submitAnswer;

    @ApiModelProperty(value = "提交时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")//后端注解格式化日期
    private Date submitTime;

    @ApiModelProperty(value = "是否正确")
    private Integer isRight;


}
