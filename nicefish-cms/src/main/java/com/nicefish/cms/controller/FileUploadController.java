package com.nicefish.cms.controller;

import com.nicefish.cms.jpa.entity.FileUploadEntity;
import com.nicefish.cms.service.IFileUploadService;
import com.nicefish.core.utils.AjaxResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

//TODO:加权限控制，没有登录的用户不能上传
@RestController
@RequestMapping("/nicefish/cms/file")
public class FileUploadController {
    private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);
    private static final String FILE_DOWNLOAD_HEADER_NAME = "Content-Disposition";
    private static final String FILE_DOWNLOAD_HEADER_VALUE = "upload;filename=";

    @Autowired
    private IFileUploadService fileUploadService;

    /**
     * 文件上传
     * @param files
     * @return
     */
    @PostMapping(value = "/upload")
    public AjaxResult upLoad(MultipartFile[] files) {
        return AjaxResult.success(fileUploadService.upload(files));
    }

    /**
     * 文件下载
     * TODO:读取文件的逻辑似乎应该移动到 Service 里面去
     * @param id
     * @param response
     * @return
     * @throws UnsupportedEncodingException
     */
    @GetMapping(value = "/download/{id}")
    public AjaxResult download(@PathVariable("id") Integer id, HttpServletResponse response) throws UnsupportedEncodingException {
        FileUploadEntity fileUploadEntity = fileUploadService.getFileById(id);
        File storedFile = new File(fileUploadEntity.getPath());//TODO:这里似乎不应该直接取路径
        if (!storedFile.exists()){
            return AjaxResult.failure("文件不存在");
        }

        String fileNameEncoded = URLEncoder.encode(storedFile.getName(), StandardCharsets.UTF_8.name());
        // 设置下载后文件名
        response.addHeader(FILE_DOWNLOAD_HEADER_NAME, FILE_DOWNLOAD_HEADER_VALUE + fileNameEncoded);

        byte[] buffer = new byte[1024];
        FileInputStream fileInputStream = null;
        BufferedInputStream bufferedInputStream = null;
        try {
            fileInputStream = new FileInputStream(storedFile);
            bufferedInputStream = new BufferedInputStream(fileInputStream);
            OutputStream os = response.getOutputStream();
            int i = bufferedInputStream.read(buffer);
            while (i != -1) {
                os.write(buffer, 0, i);
                i = bufferedInputStream.read(buffer);
            }
            return AjaxResult.success();
        } catch (Exception e) {
            logger.error(e.getStackTrace().toString());
            return AjaxResult.failure();
        } finally {
            if (bufferedInputStream != null) {
                try {
                    bufferedInputStream.close();
                } catch (IOException e) {
                    logger.error(e.getStackTrace().toString());
                    return AjaxResult.failure();
                }
            }
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    logger.error(e.getStackTrace().toString());
                    return AjaxResult.failure();
                }
            }
        }
    }
}
