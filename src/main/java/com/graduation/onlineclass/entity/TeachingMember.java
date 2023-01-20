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
 * @since 2023-01-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="TeachingMember对象", description="")
public class TeachingMember implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "学生的id")
    private String uid;

    @ApiModelProperty(value = "所上课堂的id")
    private Long teachingId;

    @ApiModelProperty(value = "表的id")
    private Long id;


}
