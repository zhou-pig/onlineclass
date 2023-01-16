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
@ApiModel(value="Discussion对象", description="")
public class Discussion implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "teaching_id", type = IdType.AUTO)
    private Long teachingId;

    private Long discussionId;

    @ApiModelProperty(value = "讨论的内容")
    private String content;


}
