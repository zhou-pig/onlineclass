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
@ApiModel(value="Teaching对象", description="")
public class Teaching implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long cId;

    @ApiModelProperty(value = "老师的id")
    private Long uId;

    @TableId(value = "teaching_id")
    private Long teachingId;


}
