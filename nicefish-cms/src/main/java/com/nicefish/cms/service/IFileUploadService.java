package com.nicefish.cms.service;

import com.nicefish.cms.jpa.entity.FileUploadEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface IFileUploadService {
    List<FileUploadEntity> upload(MultipartFile[] files);

    FileUploadEntity getFileById(Integer id);
}
