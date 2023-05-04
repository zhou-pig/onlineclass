package com.graduation.onlineclass.mapper;

import com.graduation.onlineclass.entity.MyFile;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 周富雄
 * @since 2023-02-03
 */
@Mapper
public interface MyFileMapper extends BaseMapper<MyFile> {

    List<MyFile> getPPT(Long teachingEachId);

    List<MyFile> getVideo(Long teachingEachId);
}
