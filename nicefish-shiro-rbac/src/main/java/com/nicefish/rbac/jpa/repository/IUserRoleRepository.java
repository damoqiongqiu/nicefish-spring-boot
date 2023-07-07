package com.nicefish.rbac.jpa.repository;

import com.nicefish.rbac.jpa.entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @see <a href="https://docs.spring.io/spring-data/jpa/docs/2.1.10.RELEASE/reference/html/">JPA DOC</a>
 * @author 大漠穷秋
 */
public interface IUserRoleRepository extends PagingAndSortingRepository<UserRoleEntity, Long>, JpaSpecificationExecutor {
    void deleteByRoleIdAndUserIdIsIn(Long roleId,Long[] userIds);
    void deleteAllByUserIdIsIn(Long[] userIds);
    void deleteAllByRoleId(Long roleId);
}
