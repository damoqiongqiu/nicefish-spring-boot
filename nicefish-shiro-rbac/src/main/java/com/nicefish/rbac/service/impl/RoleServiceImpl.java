package com.nicefish.rbac.service.impl;

import com.nicefish.core.utils.AjaxResult;
import com.nicefish.rbac.jpa.entity.*;
import com.nicefish.rbac.jpa.repository.*;
import com.nicefish.rbac.service.IRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
public class RoleServiceImpl implements IRoleService {
    private static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Autowired
    private IRoleRepository roleRepository;

    @Autowired
    private IUserRoleRepository userRoleRepository;

    @Autowired
    private IRoleComponentRepository roleComponentRepository;

    @Autowired
    private IRoleApiRepository roleApiRepository;

    @Autowired
    private IApiPermissionRepository apiPermissionRepository;

    @Autowired
    private IComponentPermissionRepository componentPermissionRepository;

    @Override
    @Transactional
    public AjaxResult createRole(RoleEntity roleEntity) {
        //TODO:数据校验
        roleEntity=this.roleRepository.save(roleEntity);
        return AjaxResult.success(roleEntity);
    }

    @Override
    @Transactional
    public AjaxResult editRole(RoleEntity newRoleEntity) {
        //TODO:数据校验

        RoleEntity oldEntity=this.roleRepository.findDistinctByRoleId(newRoleEntity.getRoleId());

        //处理服务端 API 权限关联关系
        //TODO:自动级联更新？
        //先删掉现有的 API 权限关联关系
        this.roleApiRepository.deleteAllByRoleId(oldEntity.getRoleId());
        List<RoleApiEntity> newRoleApiPermList=new ArrayList<>();
        List<ApiPermissionEntity> newPermApiEntities=newRoleEntity.getApiEntities();
        newPermApiEntities.forEach(entity->{
            RoleApiEntity newRoleApiEntity=new RoleApiEntity();
            newRoleApiEntity.setRoleId(newRoleEntity.getRoleId());
            newRoleApiEntity.setApiPermissionId(entity.getApiPermissionId());
            newRoleApiPermList.add(newRoleApiEntity);
        });
        logger.debug(newRoleApiPermList.toString());
        this.roleApiRepository.saveAll(newRoleApiPermList);

        //处理前端组件权限关联关系
        //TODO:自动级联更新？
        //先删掉现有的组件权限关联关系
        this.roleComponentRepository.deleteAllByRoleId(oldEntity.getRoleId());
        List<RoleComponentEntity> newRoleCompPermList=new ArrayList<>();
        List<ComponentPermissionEntity> componentEntities=newRoleEntity.getComponentEntities();
        componentEntities.forEach(entity->{
            RoleComponentEntity newRoleCompEntity=new RoleComponentEntity();
            newRoleCompEntity.setRoleId(newRoleEntity.getRoleId());
            newRoleCompEntity.setComponentId(entity.getCompPermId());
            newRoleCompPermList.add(newRoleCompEntity);
        });
        logger.debug(newRoleCompPermList.toString());
        this.roleComponentRepository.saveAll(newRoleCompPermList);

        //TODO:处理用户角色关联关系
        //TODO:自动级联更新？
        this.roleRepository.save(newRoleEntity);
        return AjaxResult.success("保存成功");
    }

    @Override
    @Transactional
    public int deleteRole(Integer roleId) {
        return this.roleRepository.deleteByRoleId(roleId);
    }

    @Override
    @Transactional
    public AjaxResult deleteRoleByIds(String ids){
        //TODO:已经关联的角色不能删除
        //TODO:系统内置的角色不能删除
        String[] roleIds=ids.split(",");
        Integer[] longIds=new Integer[roleIds.length];
        for (int i = 0; i < roleIds.length; i++) {
            longIds[i]=Integer.parseInt(roleIds[i]);
        }
        this.roleRepository.deleteAllByRoleIdIn(longIds);
        return new AjaxResult(true,"删除成功");
    }

    @Override
    public Page<RoleEntity> getRoleListPaging(RoleEntity roleEntity, Pageable pageable) {
        return this.roleRepository.findAll(new Specification<RoleEntity>() {
            @Override
            public Predicate toPredicate(Root<RoleEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates=new ArrayList<>();
                if(!StringUtils.isEmpty(roleEntity.getRoleName())){
                    predicates.add(criteriaBuilder.like(root.get("roleName"),"%"+roleEntity.getRoleName()+"%"));
                }
//                if(!StringUtils.isEmpty(roleEntity.getRoleKey())){
//                    predicates.add(criteriaBuilder.like(root.get("roleKey"),"%"+roleEntity.getRoleKey()+"%"));
//                }
                if(!StringUtils.isEmpty(roleEntity.getStatus())){
                    predicates.add(criteriaBuilder.equal(root.get("status"),roleEntity.getStatus()));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        },pageable);
    }

    @Override
    public RoleEntity getRoleById(Integer roleId) {
        return this.roleRepository.findDistinctByRoleId(roleId);
    }

    @Override
    public Page<UserEntity> getAuthUsersByRoleId(Integer roleId, Pageable pageable) {
        return this.roleRepository.findUserEntitiesByRoleId(roleId,pageable);
    }

    @Override
    @Transactional
    public AjaxResult deleteAuthUsers(RoleEntity roleEntity) {
        this.userRoleRepository.deleteAllByRoleId(roleEntity.getRoleId());
        return new AjaxResult(true,"删除成功");
    }

    @Override
    @Transactional
    public AjaxResult deleteAuthUsers(Integer roleId) {
        this.userRoleRepository.deleteAllByRoleId(roleId);
        return new AjaxResult(true,"删除成功");
    }

    @Override
    @Transactional
    public AjaxResult deleteAuthUsers(Integer roleId, Integer[] userIds) {
        this.userRoleRepository.deleteByRoleIdAndUserIdIsIn(roleId,userIds);
        return new AjaxResult(true,"删除成功");
    }

    @Override
    @Transactional
    public AjaxResult addAuthUsers(Integer roleId, Integer[] userIds) {
        List<UserRoleEntity> list=new ArrayList<>();
        for(int i=0;i<userIds.length;i++){
            UserRoleEntity userRoleEntity=new UserRoleEntity();
            userRoleEntity.setRoleId(roleId);
            userRoleEntity.setUserId(userIds[i]);
            list.add(userRoleEntity);
        }
        this.userRoleRepository.saveAll(list);
        return new AjaxResult(true,"保存成功");
    }

    @Override
    @Transactional
    public AjaxResult addAuthUsers(Integer roleId, List<UserEntity> userEntities) {
        List<UserRoleEntity> list=new ArrayList<>();
        userEntities.forEach(userEntity -> {
            UserRoleEntity userRoleEntity=new UserRoleEntity();
            userRoleEntity.setRoleId(roleId);
            userRoleEntity.setUserId(userEntity.getUserId());
            list.add(userRoleEntity);
        });
        this.userRoleRepository.saveAll(list);
        return new AjaxResult(true,"保存成功");
    }

    @Override
    public boolean isRoleNameUnique(String roleName) {
        return this.roleRepository.findDistinctByRoleName(roleName)==null?true:false;
    }

    @Override
    @Transactional
    public AjaxResult addComponentPermission(RoleEntity roleEntity, ComponentPermissionEntity componentPermissionEntity) {
        RoleComponentEntity roleComponentEntity=new RoleComponentEntity();
        roleComponentEntity.setRoleId(roleEntity.getRoleId());
        roleComponentEntity.setComponentId(componentPermissionEntity.getCompPermId());
        this.roleComponentRepository.save(roleComponentEntity);
        return new AjaxResult(true,"保存成功");
    }

    @Override
    @Transactional
    public AjaxResult addComponentPermissions(RoleEntity roleEntity, List<ComponentPermissionEntity> componentEntities) {
        List<RoleComponentEntity> list=new ArrayList<>();
        list.forEach(componentEntity -> {
            RoleComponentEntity roleComponentEntity=new RoleComponentEntity();
            roleComponentEntity.setRoleId(roleEntity.getRoleId());
            roleComponentEntity.setComponentId(componentEntity.getComponentId());
            list.add(roleComponentEntity);
        });
        this.roleComponentRepository.saveAll(list);
        return new AjaxResult(true,"保存成功");
    }

    @Override
    @Transactional
    public AjaxResult addApiPermission(RoleEntity roleEntity, ApiPermissionEntity apiPermissionEntity) {
        RoleApiEntity list=new RoleApiEntity();
        list.setRoleId(roleEntity.getRoleId());
        list.setApiPermissionId(apiPermissionEntity.getApiPermissionId());
        this.roleApiRepository.save(list);
        return new AjaxResult(true,"保存成功");
    }

    @Override
    @Transactional
    public AjaxResult deleteComponentPermission(RoleEntity roleEntity, ComponentPermissionEntity componentPermissionEntity) {
        this.roleComponentRepository.deleteByRoleIdAndCompPermId(roleEntity.getRoleId(), componentPermissionEntity.getCompPermId());
        return new AjaxResult(true,"删除成功");
    }

    @Override
    @Transactional
    public AjaxResult deleteComponentPermissions(Integer roleId, Integer[] componentIds) {
        this.roleComponentRepository.deleteByRoleIdAndCompPermIdIsIn(roleId,componentIds);
        return new AjaxResult(true,"删除成功");
    }

    @Override
    @Transactional
    public AjaxResult deleteApiPermission(RoleEntity roleEntity, ApiPermissionEntity apiPermissionEntity) {
        this.roleApiRepository.deleteByRoleIdAndApiPermissionId(roleEntity.getRoleId(), apiPermissionEntity.getApiPermissionId());
        return new AjaxResult(true,"删除成功");
    }

    @Override
    @Transactional
    public AjaxResult deleteApiPermissions(Integer roleId, Integer[] componentIds) {
        this.roleApiRepository.deleteByRoleIdAndApiPermissionIdIsIn(roleId,componentIds);
        return new AjaxResult(true,"删除成功");
    }

    @Override
    public Iterable<String> getPermStrsByRoleId(Integer roleId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllPermissionsByRoleId'");
    }

    @Override
    public AjaxResult deleteAllPermissionsByRoleId(Integer roleId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteAllPermissionsByRoleId'");
    }
}
