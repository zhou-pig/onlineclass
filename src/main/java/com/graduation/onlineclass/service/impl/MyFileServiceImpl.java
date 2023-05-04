package com.graduation.onlineclass.service.impl;

import com.graduation.onlineclass.entity.MyFile;
import com.graduation.onlineclass.mapper.MyFileMapper;
import com.graduation.onlineclass.service.MyFileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 周富雄
 * @since 2023-02-03
 */
@Service
public class MyFileServiceImpl extends ServiceImpl<MyFileMapper, MyFile> implements MyFileService {
    @Autowired
    MyFileMapper myFileMapper;

    public List<MyFile> getFile(Long teachingEachId,String type) {
        if(type.equals("file"))
            return myFileMapper.getPPT(teachingEachId);
        else if(type.equals("video"))
            return myFileMapper.getVideo(teachingEachId);
        return null;
    }

    public boolean deleteFileById(Long id) {
        MyFile myFile = myFileMapper.selectById(id);
        File file = new File(myFile.getPath() + myFile.getName());
        // 如果文件存在，且删除成功
        return file.exists() && file.delete() && myFileMapper.deleteById(id) > 0;
    }
}
