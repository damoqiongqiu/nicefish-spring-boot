package com.nicefish.cms.service.impl;

import com.nicefish.cms.jpa.entity.AttachmentEntity;
import com.nicefish.cms.jpa.repository.IAttachmentRepository;
import com.nicefish.cms.service.IAttachmentService;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;


@Transactional
@Service
public class AttachmentServiceImpl implements IAttachmentService {

    @Value("${nicefish.uploadPath}")
    private String uploadPath;

    @Autowired
    private IAttachmentRepository attachmentRepository;


    @Override
    public AttachmentEntity upload(MultipartFile file) {

        String name = file.getOriginalFilename();
        if (StringUtils.isBlank(name)) {
            return null;
        }
        //后缀名
        String suffix = StringUtils.isNotBlank(FilenameUtils.getExtension(name)) ? FilenameUtils.getExtension(name) : "";
        //文件重命名，防止覆盖
        String targetFileName = System.currentTimeMillis() + "." + suffix;
        File targetFile = new File(uploadPath, targetFileName);
        if (!targetFile.getParentFile().exists()) {
            targetFile.getParentFile().mkdirs();
        }
        try {
            //Transfer the received file to the given destination file.
            file.transferTo(targetFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        AttachmentEntity attachment = new AttachmentEntity(null, targetFile.getPath(), name, suffix);
        return attachmentRepository.save(attachment);
    }

    @Override
    public AttachmentEntity getAttachmentById(Integer id) {
        AttachmentEntity attachmentEntity = attachmentRepository.findDistinctById(id);
        return attachmentEntity;
    }
}
