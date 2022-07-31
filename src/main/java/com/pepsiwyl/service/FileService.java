package com.pepsiwyl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pepsiwyl.pojo.File;

import java.util.List;

/**
 * @author by pepsi-wyl
 * @date 2022-07-30 16:49
 */

public interface FileService extends IService<File> {

    // 根据用户ID获取文件列表
    List<File> getFilesByUserId(Integer userId);

    // 插入文件数据
    int saveFile(File file);

    // 根据文件ID查找文件
    File getFileById(Integer fileId);

    // 更新文件数据
    int updateFileById(File file);

    // 删除文件数据
    int deleteFileById(Integer fileId);

}
