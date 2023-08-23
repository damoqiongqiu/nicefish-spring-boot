package com.nicefish.cms.jpa.repository;

import com.nicefish.cms.jpa.entity.FileUploadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFileUploadRepository extends PagingAndSortingRepository<FileUploadEntity, Integer>, JpaRepository<FileUploadEntity, Integer> {
    FileUploadEntity findDistinctById(Integer id);
}
