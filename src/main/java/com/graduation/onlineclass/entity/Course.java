package com.graduation.onlineclass.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
@ApiModel(value="Course对象", description="")
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "课程id")
    @TableId(value = "c_id", type = IdType.AUTO)
    private Long cId;

    @ApiModelProperty(value = "课程名")
    private String courseName;

    @ApiModelProperty(value = "课程基本信息")
    private String courseBasicInfo;

    @ApiModelProperty(value = "学分")
    private Integer credict;


}
