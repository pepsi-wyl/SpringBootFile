package com.pepsiwyl.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pepsiwyl.mapper.FileMapper;
import com.pepsiwyl.pojo.File;
import com.pepsiwyl.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author by pepsi-wyl
 * @date 2022-07-30 16:52
 */

@Transactional
@Service("fileService")
public class FileServiceImpl extends ServiceImpl<FileMapper, File> implements FileService {
    private final FileMapper fileMapper;

    public FileServiceImpl(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    @Override
    public List<File> getFilesByUserId(Integer userId) {
        return fileMapper.getFilesByUserId(userId);
    }

    @Override
    public int saveFile(File file) {
        return fileMapper.saveFile(file);
    }

    @Override
    public File getFileById(Integer fileId) {
        return fileMapper.getFileById(fileId);
    }

    @Override
    public int updateFileById(File file) {
        return fileMapper.updateFileById(file);
    }

    @Override
    public int deleteFileById(Integer fileId) {
        return fileMapper.deleteFileById(fileId);
    }

}
