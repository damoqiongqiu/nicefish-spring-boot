package com.nicefish.search.repository;

import com.nicefish.search.entity.UserSearchEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSearchRepository extends ElasticsearchRepository<UserSearchEntity, String> {
}