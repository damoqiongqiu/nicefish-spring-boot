package com.nicefish.cms.jpa.repository;

import com.nicefish.cms.jpa.entity.AttachmentEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAttachmentRepository extends PagingAndSortingRepository<AttachmentEntity, Integer> {


    AttachmentEntity findDistinctById(Integer id);
}
