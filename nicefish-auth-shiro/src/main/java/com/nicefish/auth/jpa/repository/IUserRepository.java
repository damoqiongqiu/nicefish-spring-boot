package com.nicefish.auth.jpa.repository;

import com.nicefish.auth.jpa.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @see <a href="https://docs.spring.io/spring-data/jpa/docs/2.1.10.RELEASE/reference/html/">JPA DOC</a>
 * @author 大漠穷秋
 */
public interface IUserRepository extends PagingAndSortingRepository<UserEntity, Long>, JpaSpecificationExecutor {

    UserEntity findDistinctByUserId(Long userId);

    UserEntity findDistinctByUserName(String userName);

    UserEntity findDistinctByEmail(String email);

    UserEntity findDistinctByCellphone(String cellphone);

    UserEntity findDistinctByUserNameOrEmailOrCellphone(String userName,String email,String cellphone);

    Page<UserEntity> findAllByNickName(String nickName,Pageable pageable);

    Page<UserEntity> findAllByUserNameOrNickNameOrEmailOrCellphone(String userName,String nickName,String email,String cellphone, Pageable pageable);

    Iterable<UserEntity> findAll(Sort sort);

    Page<UserEntity> findAll(Pageable pageable);

    int deleteByUserId(Long userId);

    int deleteByUserIdIn(Long[] userIds);

    int deleteAllByUserId(Long[] ids);
}