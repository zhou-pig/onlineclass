package com.graduation.onlineclass.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
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
 * @since 2023-02-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="File对象", description="")
@NoArgsConstructor
@AllArgsConstructor
public class MyFile implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "文件名")
    private String name;

    @ApiModelProperty(value = "文件id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "文件类型（视频、PPT、图片）")
    private String type;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")//后端注解格式化日期
    @ApiModelProperty(value = "上传时间")
    private Date uploadTime;

    @ApiModelProperty(value = "上传者id")
    private Long uploader;

    @ApiModelProperty(value = "所属课堂id")
    private Long teachingEachId;

    @ApiModelProperty(value = "文件路径")
    private String path;

    @ApiModelProperty(value = "文件大小,单位：字节")
    private Long fileSize;
}
