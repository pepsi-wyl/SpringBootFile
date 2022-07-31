package com.pepsiwyl.controller;

import com.pepsiwyl.pojo.File;
import com.pepsiwyl.service.FileService;
import com.pepsiwyl.utils.FileUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author by pepsi-wyl
 * @date 2022-07-30 15:54
 */

@Slf4j

@Controller("fileController")
@RequestMapping(name = "文件控制器", path = "/file")
public class FileController {
    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("/allFile")
    public ModelAndView allFile(HttpSession session) {

        // 从Session获取username
        String username = (String) session.getAttribute("user");
        // 从Session获取UserId
        Integer userId = (Integer) session.getAttribute("userId");
        // 根据UserID查询files
        List<File> files = fileService.getFilesByUserId(userId);

        // 跳转视图和返回数据
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("files", files);
        modelAndView.addObject("username", username);
        modelAndView.setViewName("allFile");

        return modelAndView;
    }

    /**
     * 上传文件
     */
    @SneakyThrows
    @PostMapping("/upload")
    public String upload(@RequestPart("file") MultipartFile file, HttpSession session) {

        // userId
        Integer userId = (Integer) session.getAttribute("userId");
        // 文件旧名称
        String oldFilename = file.getOriginalFilename();
        // 文件后缀
        String extension = "." + FilenameUtils.getExtension(oldFilename);
        // 文件新名称  Day+UUID+后缀
        String newFilename = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + UUID.randomUUID().toString().replace("-", "") + extension;
        // 文件大小
        String size = FileUtils.formatFileSize(file.getSize());
        // 文件类型
        String type = file.getContentType();

        // 动态处理文件夹
        String dataFormat = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String bashPath = ResourceUtils.getURL("classpath:").getPath() + "/static/files";
        java.io.File dataDir = new java.io.File(bashPath + "/" + dataFormat); // 路径转化为文件夹
        if (!dataDir.exists()) dataDir.mkdirs();         // 文件夹不存在则创建

        // 文件上传
        file.transferTo(new java.io.File(dataDir, newFilename));

        // 文件信息放入数据库
        File upFile = File.builder().oldFilename(oldFilename).newFilename(newFilename).path("/files/" + dataFormat).size(size).ext(extension).type(type).uploadTime(new Date()).isImg(type.startsWith("image") ? "是" : "否").downCount(0).userId(userId).build();
        fileService.saveFile(upFile);

        return "redirect:/file/allFile";
    }

    @SneakyThrows
    @GetMapping("/download")
    public void download(@RequestParam("openStyle") String openStyle, @RequestParam("id") Integer id, HttpServletResponse response) {

        // 得到数据库中的该文件数据记录
        File fileById = fileService.getFileById(id);

        // 更新下载次数
        int i = openStyle.equals("attachment") ? fileService.updateFileById(fileById.setDownCount(fileById.getDownCount() + 1)) : 0;

        // 附件下载
        response.setHeader("Content-Disposition", openStyle + ";filename=" + URLEncoder.encode(fileById.getOldFilename(), "UTF-8"));
        //创建文件输入流
        InputStream is = new FileInputStream(new java.io.File(ResourceUtils.getURL("classpath:").getPath() + "/static" + fileById.getPath(), fileById.getNewFilename()));
        // 获取相应输出流
        ServletOutputStream os = response.getOutputStream();
        // 文件拷贝
        IOUtils.copy(is, os);
        IOUtils.closeQuietly(is);
        IOUtils.closeQuietly(os);

//        return "redirect:/file/allFile";
    }

    @SneakyThrows
    @GetMapping("/delete")
    public String delete(@RequestParam("id") Integer id) {

        // 得到数据库中的该文件数据记录
        File fileById = fileService.getFileById(id);

        // 删除服务器的文件
        java.io.File deleteFile = new java.io.File(ResourceUtils.getURL("classpath:").getPath() + "/static" + fileById.getPath(), fileById.getNewFilename());
        if (deleteFile.exists()) deleteFile.delete();

        // 删除数据库中的文件记录
        int i = fileService.deleteFileById(fileById.getId());

        return "redirect:/file/allFile";
    }

}
