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
 * @since 2023-04-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="ClassInfo对象", description="")
public class ClassInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "班级id")
    private Long id;

    @ApiModelProperty(value = "班级名称")
    private String name;


}
