package com.graduation.onlineclass.entity;

import java.util.Date;
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
@ApiModel(value="QuizRecords对象", description="")
public class QuizRecords implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "随堂测试作答记录的id")
    private Long qrId;

    @ApiModelProperty(value = "题目id")
    private Long qId;

    @ApiModelProperty(value = "提交者的id")
    private Long uId;

    @ApiModelProperty(value = "提交的答案")
    private String submitAnswer;

    @ApiModelProperty(value = "提交时间")
    private Date submitTime;

    @ApiModelProperty(value = "是否正确")
    private Integer isRight;


}
