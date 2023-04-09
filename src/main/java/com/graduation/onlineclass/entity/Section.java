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
 * @since 2023-04-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Section对象", description="")
public class Section implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "章节id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "章节索引")
    private Integer index;

    @ApiModelProperty(value = "章节名称")
    private String name;

    @ApiModelProperty(value = "所属课程")
    private Long tId;


}
