package com.graduation.onlineclass.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2023-01-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="TeachingMember对象", description="")
@AllArgsConstructor
@NoArgsConstructor
public class TeachingMember implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "学生的id")
    private Long uId;

    @ApiModelProperty(value = "所上课堂的id")
    private Long teachingId;

    @TableId
    @ApiModelProperty(value = "表的id")
    private Long id;

}
