package com.graduation.onlineclass.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
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
@ApiModel(value="UserInfo对象", description="")
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    @ApiModelProperty(value = "用户id，也就是account")
    private Long uId;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "男、女、未知")
    private String gender;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "电话")
    private String phone;

    @ApiModelProperty(value = "微信用户唯一标识")
    private String wxCode;


    public UserInfo(String wxCode) {
        this.wxCode = wxCode;
    }

    public UserInfo( String wxCode,Long uId) {
        this.wxCode = wxCode;
        this.uId = uId;
    }
}
