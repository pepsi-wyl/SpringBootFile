package com.pepsiwyl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pepsiwyl.pojo.File;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author by pepsi-wyl
 * @date 2022-07-30 16:40
 */

@Repository("fileMapper")
public interface FileMapper extends BaseMapper<File> {

    // 根据用户ID获取文件列表
    List<File> getFilesByUserId(@Param("userId") Integer userId);

    // 插入文件数据
    int saveFile(File file);

    // 根据文件ID查找文件数据
    File getFileById(@Param("fileId") Integer fileId);

    // 更新文件数据
    int updateFileById(File file);

    // 删除文件数据
    int deleteFileById(@Param("fileId") Integer fileId);

}
