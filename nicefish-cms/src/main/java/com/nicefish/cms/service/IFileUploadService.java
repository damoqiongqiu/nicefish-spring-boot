package com.nicefish.cms.service;

import com.nicefish.cms.jpa.entity.FileUploadEntity;
import org.springframework.web.multipart.MultipartFile;


public interface IFileUploadService {
    FileUploadEntity upload(MultipartFile multipartFile);

    FileUploadEntity getFileById(Integer id);
}
