package com.nicefish.cms.controller;

import com.nicefish.cms.jpa.entity.FileUploadEntity;
import com.nicefish.cms.service.IFileUploadService;
import com.nicefish.core.utils.AjaxResult;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;


@Api("cms-附件上传")
@RestController
@RequestMapping("/nicefish/cms/file")
public class FileUploadController {
    private static final String FILE_DOWNLOAD_HEADER_NAME = "Content-Disposition";
    private static final String FILE_DOWNLOAD_HEADER_VALUE = "upload;filename=";

    @Autowired
    private IFileUploadService fileUploadService;

    /**
     * 文件上传
     * @param file
     * @return
     */
    @PostMapping(value = "/upload")
    public AjaxResult upLoad(MultipartFile file) {
        if (file.isEmpty()) {
            return AjaxResult.failure("请上传附件");
        }
        //上传文件
        return AjaxResult.success(fileUploadService.upload(file));
    }

    @GetMapping(value = "/download/{id}")
    public AjaxResult download(@PathVariable("id") Integer id, HttpServletResponse resp) throws UnsupportedEncodingException {
        //TODO:下面的逻辑需要根据表结构重构一下
        FileUploadEntity fileUploadEntity = fileUploadService.getFileById(id);
        File storedFile = new File(fileUploadEntity.getPath());
        if (storedFile.exists()) {
            String fileNameEncode = URLEncoder.encode(storedFile.getName(), StandardCharsets.UTF_8.name());
            // 设置下载后文件名
            resp.addHeader(FILE_DOWNLOAD_HEADER_NAME, FILE_DOWNLOAD_HEADER_VALUE + fileNameEncode);
            byte[] buffer = new byte[1024];
            FileInputStream fileInputStream = null;
            BufferedInputStream bufferedInputStream = null;
            try {
                fileInputStream = new FileInputStream(storedFile);
                bufferedInputStream = new BufferedInputStream(fileInputStream);
                OutputStream os = resp.getOutputStream();
                int i = bufferedInputStream.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bufferedInputStream.read(buffer);
                }
                return AjaxResult.success();
            } catch (Exception e) {
                return AjaxResult.failure();
            } finally {
                if (bufferedInputStream != null) {
                    try {
                        bufferedInputStream.close();
                    } catch (IOException e) {
                        return AjaxResult.failure();
                    }
                }
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e) {
                        return AjaxResult.failure();
                    }
                }
            }
        } else {
            return AjaxResult.failure("文件不存在");
        }
    }
}
