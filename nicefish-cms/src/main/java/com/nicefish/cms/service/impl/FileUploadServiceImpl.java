package com.nicefish.cms.service.impl;

import com.nicefish.cms.jpa.entity.FileUploadEntity;
import com.nicefish.cms.jpa.repository.IFileUploadRepository;
import com.nicefish.cms.service.IFileUploadService;
import com.nicefish.cms.util.HashUtils;
import com.nicefish.core.utils.AjaxResult;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class FileUploadServiceImpl implements IFileUploadService {
    private static final Logger logger = LoggerFactory.getLogger(FileUploadServiceImpl.class);
    private static final String FILE_DOWNLOAD_HEADER_NAME = "Content-Disposition";
    private static final String FILE_DOWNLOAD_HEADER_VALUE = "upload;filename=";
    @Value("${nicefish.uploadPath}")
    private String uploadPath;

    @Autowired
    private IFileUploadRepository fileUploadRepository;

    @Override
    public List<FileUploadEntity> upload(MultipartFile[] files) {
        try {
            //确保目录存在
            File targetDir = new File(this.uploadPath);
            if (!targetDir.exists()) {
                targetDir.mkdirs();
            }

            List<FileUploadEntity> resultList = new ArrayList<>();
            for (MultipartFile file : files) {
                //取文件名
                String name = file.getOriginalFilename();
                if (StringUtils.isEmpty(name)) {
                    return null;
                }

                //取文件后缀
                String suffix = StringUtils.isNotBlank(FilenameUtils.getExtension(name)) ? FilenameUtils.getExtension(name) : "";

                //文件重命名，防止同目录文件覆盖
                String targetFileName = HashUtils.generateSaltedSha256() + "." + suffix;
                File targetFile = new File(this.uploadPath, targetFileName);
                file.transferTo(targetFile);

                //TODO:表里面还有其它字段，这里需要补全
                FileUploadEntity fileEntity = new FileUploadEntity();
                fileEntity.setDisplayName(name.replaceAll("." + suffix, ""));
                fileEntity.setFileName(targetFileName);
                fileEntity.setPath(targetFile.getPath());
                fileEntity.setFileSize(file.getSize());
                fileEntity.setFileSuffix(suffix);
                fileEntity = this.fileUploadRepository.save(fileEntity);

                resultList.add(fileEntity);
            }
            return resultList;
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.getStackTrace().toString());
        }
        return null;
    }

    @Override
    public FileUploadEntity getFileById(Integer id) {
        FileUploadEntity fileUploadEntity = this.fileUploadRepository.findDistinctById(id);
        return fileUploadEntity;
    }

    @Override
    public AjaxResult download(Integer id, HttpServletResponse response) {
        FileUploadEntity fileUploadEntity = this.getFileById(id);
        File storedFile = new File(fileUploadEntity.getPath());//TODO:这里似乎不应该直接取路径
        if (!storedFile.exists()) {
            return AjaxResult.failure("文件不存在");
        }
        String fileNameEncoded = null;
        try {
            fileNameEncoded = URLEncoder.encode(storedFile.getName(), StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            logger.error("download error:{}", e.getMessage());
        }
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
