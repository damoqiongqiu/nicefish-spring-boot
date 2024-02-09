package com.nicefish.cms.controller;

import com.nicefish.cms.service.IFileUploadService;
import com.nicefish.core.utils.AjaxResult;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.UnsupportedEncodingException;

//TODO:加权限控制，没有登录的用户不能上传
@RestController
@RequestMapping("/nicefish/cms/file")
public class FileUploadController {
    private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

    @Autowired
    private IFileUploadService fileUploadService;

    /**
     * 文件上传
     *
     * @param files
     * @return
     */
    @PostMapping(value = "/upload")
    public AjaxResult upLoad(MultipartFile[] files) {
        return AjaxResult.success(this.fileUploadService.upload(files));
    }

    /**
     * 文件下载
     *
     * @param id
     * @param response
     * @return
     * @throws UnsupportedEncodingException
     */
    @GetMapping(value = "/download/{id}")
    public AjaxResult download(@PathVariable("id") Integer id, HttpServletResponse response) throws UnsupportedEncodingException {
        return this.fileUploadService.download(id, response);
    }
}
