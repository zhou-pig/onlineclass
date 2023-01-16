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
 * @since 2023-01-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="AccountInfo对象", description="")
public class AccountInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "账号（学号、教师号）")
    private String uId;

    private String password;

    private String university;

    @ApiModelProperty(value = "学生、老师、管理员")
    private String role;

    @ApiModelProperty(value = "真实名称")
    private String realName;


}
