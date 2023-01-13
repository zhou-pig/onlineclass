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
@ApiModel(value="Discussion对象", description="")
public class Discussion implements Serializable {

    private static final long serialVersionUID=1L;

    private Long teachingId;

    private Long discussionId;


}
