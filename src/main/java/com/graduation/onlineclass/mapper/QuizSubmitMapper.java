package com.graduation.onlineclass.mapper;

import com.graduation.onlineclass.entity.QuizSubmit;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;

import java.security.Key;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 周富雄
 * @since 2023-01-16
 */
@Mapper
public interface QuizSubmitMapper extends BaseMapper<QuizSubmit> {

    List<Map<String, Object> > getByQid(Long qid);

    List<Map<String, Object>> getRightByQid(Long qid);

    List<Map<String, Object>> getUndoStudent(Long teachingId, Long qid);
}
