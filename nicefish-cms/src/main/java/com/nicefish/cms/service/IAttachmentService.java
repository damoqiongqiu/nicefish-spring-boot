package com.nicefish.cms.service;

import com.nicefish.cms.jpa.entity.AttachmentEntity;
import org.springframework.web.multipart.MultipartFile;


public interface IAttachmentService {

    AttachmentEntity upload(MultipartFile multipartFile);


    AttachmentEntity getAttachmentById(Integer id);
}
