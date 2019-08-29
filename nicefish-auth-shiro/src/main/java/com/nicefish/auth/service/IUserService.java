package com.nicefish.auth.service;

import com.nicefish.auth.exception.*;
import com.nicefish.auth.jpa.entity.RoleEntity;
import com.nicefish.auth.jpa.entity.UserEntity;
import com.nicefish.core.web.AjaxResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author 大漠穷秋
 */
public interface IUserService {

    AjaxResult createUser(UserEntity userEntity);

    AjaxResult resetPwd(Long userId);

    UserEntity setUserStatus(UserEntity userEntity);

    AjaxResult editUser(UserEntity userEntity);

    Page<UserEntity> getUserList(UserEntity userEntity, Pageable pageable);

    Page<UserEntity> getAllocatedList(RoleEntity roleEntity, UserEntity userEntity, Pageable pageable);

    Page<UserEntity> getUnallocatedList(RoleEntity roleEntity, UserEntity userEntity, Pageable pageable);

    UserEntity getUserByUserName(String userName);

    UserEntity getUserByCellphone(String cellphone);

    UserEntity getUserByEmail(String email);

    UserEntity getUserByUserId(Long userId);

    int deleteByUserId(Long userId);

    int deleteByIds(Long[] ids);

    UserEntity saveUser(UserEntity userEntity);

    UserEntity resetPwd(UserEntity userEntity);

    boolean isUserNameUnique(String userName);

    boolean isPhoneUnique(String cellphone);

    boolean isEmailUnique(String email);

    String getRoleList(Long userId);

    boolean matches(UserEntity userEntity, String newPassword);

    String encryptPassword(String username, String password, String salt);

    UserEntity checkUser(String userName, String password) throws UserNotExistsException,
            CaptchaException, UserPasswordNotMatchException,
            UserDeleteException, UserBlockedException;
}
