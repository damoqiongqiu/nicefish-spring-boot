package com.nicefish.rbac.service.impl;

import com.nicefish.rbac.constant.NiceFishAuthConstants;
import com.nicefish.rbac.exception.*;
import com.nicefish.rbac.jpa.entity.RoleEntity;
import com.nicefish.rbac.jpa.entity.UserEntity;
import com.nicefish.rbac.jpa.repository.IUserRepository;
import com.nicefish.rbac.service.IRoleService;
import com.nicefish.rbac.service.IUserService;
import com.nicefish.core.utils.AjaxResult;
import com.nicefish.core.utils.ServletUtil;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 大漠穷秋
 */
@Service
public class UserServiceImpl implements IUserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRoleService roleService2;

    @Override
    public AjaxResult resetPwd(Integer userId){
        //TODO:生成随机密码

        //TODO:生成密文链接

        //TODO:发送验证邮件

        return new AjaxResult(true,"密码重置成功");
    }

    @Override
    public UserEntity setUserStatus(UserEntity userEntity) {
        UserEntity oldEntity=this.userRepository.findDistinctByUserId(userEntity.getUserId());
        oldEntity.setPassword(userEntity.getPassword());
        oldEntity.setStatus(userEntity.getStatus());
        return this.userRepository.save(oldEntity);
    }

    //TODO:消息国际化
    @Override
    public AjaxResult editUser(UserEntity userEntity){
        if(!this.userRepository.existsById(userEntity.getUserId())){
           return new AjaxResult(false,"不存在指定的用户ID");
        }
        userEntity=this.userRepository.save(userEntity);
        return new AjaxResult(true,userEntity);
    }

    @Transactional
    @Override
    public AjaxResult createUser(UserEntity userEntity){
        String msg="";
        boolean isSuccess=true;

        if(this.userRepository.findDistinctByUserName(userEntity.getUserName())!=null){
            msg="用户名已存在";
            isSuccess=false;
        }
        if(this.userRepository.findDistinctByEmail(userEntity.getEmail())!=null){
            msg="邮箱已存在";
            isSuccess=false;
        }

        if(!StringUtils.isEmpty(userEntity.getCellphone()) && !ObjectUtils.isEmpty(this.userRepository.findDistinctTopByCellphone(userEntity.getCellphone()))){
            msg="手机号已存在";
            isSuccess=false;
        }

        AjaxResult ajaxResult =null;
        if(!isSuccess){
            ajaxResult =new AjaxResult(false,msg);
            logger.info("用户信息校验失败，"+ ajaxResult.toString());
            return ajaxResult;
        }

        //保存用户数据
        userEntity=this.userRepository.save(userEntity);

        //保存角色数据，默认普通用户
        this.roleService2.addAuthUsers(2,new Integer[]{userEntity.getUserId()});

        ajaxResult =new AjaxResult(true,userEntity);
        logger.info(ajaxResult.toString());
        return ajaxResult;
    }
    
    @Override
    public Page<UserEntity> getUserList(UserEntity userEntity, Pageable pageable) {
        return this.userRepository.findAll(new Specification<UserEntity>() {
            @Override
            public Predicate toPredicate(Root<UserEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates=new ArrayList<>();
                if(!StringUtils.isEmpty(userEntity.getUserName())){
                    predicates.add(criteriaBuilder.like(root.get("userName"),"%"+userEntity.getUserName()+"%"));
                }
                if(!StringUtils.isEmpty(userEntity.getCellphone())){
                    predicates.add(criteriaBuilder.like(root.get("cellphone"),"%"+userEntity.getCellphone()+"%"));
                }
                //FIXME:这里为什么要过滤 status?
                // if(!StringUtils.isEmpty(userEntity.getStatus())){
                //     predicates.add(criteriaBuilder.equal(root.get("status"),userEntity.getStatus()));
                // }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        },pageable);
    }

    @Override
    public Page<UserEntity> getAllocatedList(RoleEntity roleEntity,UserEntity userEntity, Pageable pageable) {
        return this.userRepository.findAll(new Specification<UserEntity>() {
            @Override
            public Predicate toPredicate(Root<UserEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates=new ArrayList<>();
                predicates.add(criteriaBuilder.equal(root.get("roleId"),roleEntity.getRoleId()));
                if(!StringUtils.isEmpty(userEntity.getUserName())){
                    predicates.add(criteriaBuilder.equal(root.get("userName"),userEntity.getUserName()));
                }
                if(!StringUtils.isEmpty(userEntity.getCellphone())){
                    predicates.add(criteriaBuilder.equal(root.get("cellphone"),userEntity.getCellphone()));
                }
                if(!StringUtils.isEmpty(userEntity.getEmail())){
                    predicates.add(criteriaBuilder.equal(root.get("email"),userEntity.getEmail()));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        },pageable);
    }

    @Override
    public Page<UserEntity> getUnallocatedList(RoleEntity roleEntity, UserEntity userEntity, Pageable pageable) {
        return this.userRepository.findAll(new Specification<UserEntity>() {
            @Override
            public Predicate toPredicate(Root<UserEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates=new ArrayList<>();
                predicates.add(criteriaBuilder.notEqual(root.get("roleId"),roleEntity.getRoleId()));
                if(!StringUtils.isEmpty(userEntity.getUserName())){
                    predicates.add(criteriaBuilder.equal(root.get("userName"),userEntity.getUserName()));
                }
                if(!StringUtils.isEmpty(userEntity.getCellphone())){
                    predicates.add(criteriaBuilder.equal(root.get("cellphone"),userEntity.getCellphone()));
                }
                if(!StringUtils.isEmpty(userEntity.getEmail())){
                    predicates.add(criteriaBuilder.equal(root.get("email"),userEntity.getEmail()));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        },pageable);
    }

    @Override
    public UserEntity getUserByUserName(String userName) {
        return this.userRepository.findDistinctByUserName(userName);
    }

    @Override
    public UserEntity getUserByCellphone(String cellphone) {
        return this.userRepository.findDistinctTopByCellphone(cellphone);
    }

    @Override
    public UserEntity getUserByEmail(String email) {
        return this.userRepository.findDistinctByEmail(email);
    }

    @Override
    public UserEntity getUserByUserId(Integer userId) {
        return this.userRepository.findDistinctByUserId(userId);
    }

    @Override
    @Transactional
    public int deleteByUserId(Integer userId) {
        UserEntity userEntity= this.userRepository.findDistinctByUserId(userId);
        //特权用户状态位-1，不准删除
        if("-1".equals(userEntity.getStatus())){
            return 0;
        }
        return this.userRepository.deleteByUserId(userId);
    }

    @Override
    public int deleteByIds(Integer[] ids) {
        return this.userRepository.deleteByUserIdIn(ids);
    }

    @Override
    public UserEntity saveUser(UserEntity userEntity) {
        return this.userRepository.save(userEntity);
    }

    @Override
    public UserEntity resetPwd(UserEntity userEntity) {
        //TODO:需要发送验证邮件
        UserEntity oldEntity=this.userRepository.findDistinctByUserId(userEntity.getUserId());
        oldEntity.setPassword(userEntity.getPassword());
        return this.userRepository.save(oldEntity);
    }

    @Override
    public boolean isUserNameUnique(String userName) {
        logger.debug(userName);
        UserEntity userEntity=this.userRepository.findDistinctByUserName(userName);
        return userEntity==null?true:false;
    }

    @Override
    public boolean isPhoneUnique(String cellphone) {
        logger.debug(cellphone);
        UserEntity userEntity=this.userRepository.findDistinctTopByCellphone(cellphone);
        return userEntity==null?true:false;
    }

    @Override
    public boolean isEmailUnique(String email) {
        logger.debug(email);
        UserEntity userEntity=this.userRepository.findDistinctByEmail(email);
        return userEntity==null?true:false;
    }

    @Override
    public String getRoleList(Integer userId) {
        return null;
    }

    @Override
    public boolean matches(UserEntity userEntity, String newPassword) {
        return userEntity.getPassword().equals(encryptPassword(userEntity.getUserName(), newPassword, userEntity.getSalt()));
    }

    @Override
    public String encryptPassword(String username, String password, String salt) {
        return new Md5Hash(username + password + salt).toHex().toString();
    }

    @Override
    public UserEntity checkUser(String userName, String password) throws UserNotExistsException,
            CaptchaException, UserPasswordNotMatchException,
            UserDeleteException, UserBlockedException {
        if (org.springframework.util.StringUtils.isEmpty(userName) || org.springframework.util.StringUtils.isEmpty(password)) {
            throw new UserNotExistsException();
        }
        if (!org.springframework.util.StringUtils.isEmpty(ServletUtil.getRequest().getAttribute(NiceFishAuthConstants.CURRENT_CAPTCHA))) {
            throw new CaptchaException();
        }
        if (password.length() < NiceFishAuthConstants.PASSWORD_MIN_LENGTH
                || password.length() > NiceFishAuthConstants.PASSWORD_MAX_LENGTH) {
            throw new UserPasswordNotMatchException();
        }
        if (userName.length() < NiceFishAuthConstants.USERNAME_MIN_LENGTH
                || userName.length() > NiceFishAuthConstants.USERNAME_MAX_LENGTH) {
            throw new UserPasswordNotMatchException();
        }

        UserEntity userEntity = null;

        if(userName.matches(NiceFishAuthConstants.EMAIL_PATTERN)){
            userEntity = this.getUserByEmail(userName);
        }else if(userName.matches(NiceFishAuthConstants.MOBILE_PHONE_NUMBER_PATTERN)){
            userEntity=this.getUserByCellphone(userName);
        }else{
            userEntity=this.getUserByUserName(userName);
        }

        if (userEntity == null) {
            throw new UserNotExistsException();
        }

        //校验加密后的密码
        if(!this.matches(userEntity,password)){
            throw new UserPasswordNotMatchException();
        }

        this.saveUser(userEntity);
        return userEntity;
    }
}
