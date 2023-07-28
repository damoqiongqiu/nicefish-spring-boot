package com.nicefish.cms.service.impl;

import com.nicefish.cms.jpa.entity.FileUploadEntity;
import com.nicefish.cms.jpa.repository.IFileUploadRepository;
import com.nicefish.cms.service.IFileUploadService;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;

@Transactional
@Service
public class FileUploadServiceImpl implements IFileUploadService {
    private static final Logger logger = LoggerFactory.getLogger(FileUploadServiceImpl.class);

    @Value("${nicefish.uploadPath}")
    private String uploadPath;

    @Autowired
    private IFileUploadRepository attachmentRepository;

    @Override
    public FileUploadEntity upload(MultipartFile file) {
        try {
            //取文件名
            String name = file.getOriginalFilename();
            if (StringUtils.isEmpty(name)) {
                return null;
            }

            //后缀
            String suffix = StringUtils.isNotBlank(FilenameUtils.getExtension(name)) ? FilenameUtils.getExtension(name) : "";

            //文件重命名，防止覆盖，TODO:用 hash 生成文件名
            String targetFileName = System.currentTimeMillis() + "." + suffix;
            File targetFile = new File(uploadPath, targetFileName);
            if (!targetFile.getParentFile().exists()) {
                targetFile.getParentFile().mkdirs();
            }
            file.transferTo(targetFile);

            //TODO:表里面还有其它字段，这里需要补全
            FileUploadEntity fileEntity = new FileUploadEntity();
            fileEntity.setDisplayName(name);
            fileEntity.setFileName(targetFileName);
            fileEntity.setPath(targetFile.getPath());
            fileEntity.setFileSize(file.getSize());
            fileEntity.setFileSuffix(suffix);
            return attachmentRepository.save(fileEntity);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.getStackTrace().toString());
        }
        return null;
    }

    @Override
    public FileUploadEntity getFileById(Integer id) {
        FileUploadEntity fileUploadEntity = attachmentRepository.findDistinctById(id);
        return fileUploadEntity;
    }
}
