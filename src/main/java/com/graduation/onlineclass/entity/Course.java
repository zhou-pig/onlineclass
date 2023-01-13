package com.graduation.onlineclass.entity;

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
@ApiModel(value="Course对象", description="")
public class Course implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "课程id")
    private Long cId;

    @ApiModelProperty(value = "课程名")
    private String courseName;

    @ApiModelProperty(value = "课程基本信息")
    private String courseBasicInfo;

    @ApiModelProperty(value = "学分")
    private Integer credict;


}
