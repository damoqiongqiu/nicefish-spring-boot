package com.nicefish.rbac.service.impl;

import com.nicefish.rbac.jpa.entity.*;
import com.nicefish.rbac.jpa.repository.IApiRepository;
import com.nicefish.rbac.jpa.repository.IComponentRepository;
import com.nicefish.rbac.jpa.repository.IRoleApiRepository;
import com.nicefish.rbac.jpa.repository.IRoleComponentRepository;
import com.nicefish.rbac.jpa.repository.IRoleRepository;
import com.nicefish.rbac.jpa.repository.IUserRoleRepository;
import com.nicefish.rbac.service.IRoleService;
import com.nicefish.core.utils.AjaxResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
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
    @Autowired
    private IRoleRepository roleRepository;

    @Autowired
    private IUserRoleRepository userRoleRepository;

    @Autowired
    private IRoleComponentRepository roleComponentRepository;

    @Autowired
    private IRoleApiRepository roleApiRepository;

    @Override
    @Transactional
    public AjaxResult createRole(RoleEntity roleEntity) {
        if(!this.isRoleKeyUnique(roleEntity.getRoleKey())){
            return new AjaxResult(false,"角色KEY已存在");
        }
        this.roleRepository.save(roleEntity);
        return new AjaxResult(true,roleEntity);
    }

    @Override
    @Transactional
    public int deleteRole(Integer roleId) {
        RoleEntity roleEntity= this.roleRepository.findDistinctByRoleId(roleId);
        //特权角色状态位-1，不准删除
        if(roleEntity.getStatus()==-1){
            return 0;
        }
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
    @Transactional
    public AjaxResult editRole(RoleEntity roleEntity) {
        RoleEntity oldEntity=this.roleRepository.findDistinctByRoleId(roleEntity.getRoleId());
        BeanUtils.copyProperties(roleEntity,oldEntity);
        this.roleRepository.save(oldEntity);

        // this.roleComponentRepository.deleteAllByRoleId(oldEntity.getRoleId());
        // this.roleApiRepository.deleteAllByRoleId(oldEntity.getRoleId());

        // List<PermissionEntity> list=oldEntity.getPermissionEntities();
        // if(!CollectionUtils.isEmpty(list)){
        //     List<RolePermissionEntity> rolePermissionEntities=new ArrayList<RolePermissionEntity>();
        //     for (PermissionEntity permissionEntity : list) {
        //         RolePermissionEntity rolePermissionEntity=new RolePermissionEntity();
        //         rolePermissionEntity.setRoleId(oldEntity.getRoleId());
        //         rolePermissionEntity.setPermissionId(permissionEntity.getPermissionId());
        //         rolePermissionEntities.add(rolePermissionEntity);
        //     }
        //     this.rolePermissionRepository.saveAll(rolePermissionEntities);
        // }

        return new AjaxResult(true,"保存成功");
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
                if(!StringUtils.isEmpty(roleEntity.getRoleKey())){
                    predicates.add(criteriaBuilder.like(root.get("roleKey"),"%"+roleEntity.getRoleKey()+"%"));
                }
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
    public boolean isRoleKeyUnique(String roleKey) {
        return this.roleRepository.findDistinctByRoleKey(roleKey)==null?true:false;
    }

    @Override
    public boolean isRoleNameUnique(String roleName) {
        return this.roleRepository.findDistinctByRoleName(roleName)==null?true:false;
    }

    @Override
    @Transactional
    public AjaxResult addComponentPermission(RoleEntity roleEntity, ComponentEntity componentEntity) {
        RoleComponentEntity roleComponentEntity=new RoleComponentEntity();
        roleComponentEntity.setRoleId(roleEntity.getRoleId());
        roleComponentEntity.setComponentId(componentEntity.getComponentId());
        this.roleComponentRepository.save(roleComponentEntity);
        return new AjaxResult(true,"保存成功");
    }

    @Override
    @Transactional
    public AjaxResult addComponentPermissions(RoleEntity roleEntity, List<ComponentEntity> componentEntities) {
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
    public AjaxResult addApiPermission(RoleEntity roleEntity, ApiEntity apiEntity) {
        RoleApiEntity list=new RoleApiEntity();
        list.setRoleId(roleEntity.getRoleId());
        list.setApiId(apiEntity.getApiId());
        this.roleApiRepository.save(list);
        return new AjaxResult(true,"保存成功");
    }

    @Override
    @Transactional
    public AjaxResult addApiPermissions(RoleEntity roleEntity, List<ApiEntity> apiEntities) {
        List<RoleApiEntity> list=new ArrayList<>();
        list.forEach(apiEntity -> {
            RoleApiEntity roleApiEntity=new RoleApiEntity();
            roleApiEntity.setRoleId(roleEntity.getRoleId());
            roleApiEntity.setApiId(apiEntity.getApiId());
            list.add(roleApiEntity);
        });
        this.roleApiRepository.saveAll(list);
        return new AjaxResult(true,"保存成功");
    }

    @Override
    @Transactional
    public AjaxResult deleteComponentPermission(RoleEntity roleEntity, ComponentEntity componentEntity) {
        this.roleComponentRepository.deleteByRoleIdAndComponentId(roleEntity.getRoleId(),componentEntity.getComponentId());
        return new AjaxResult(true,"删除成功");
    }

    @Override
    @Transactional
    public AjaxResult deleteComponentPermissions(Integer roleId, Integer[] componentIds) {
        this.roleComponentRepository.deleteByRoleIdAndComponentIdIsIn(roleId,componentIds);
        return new AjaxResult(true,"删除成功");
    }

    @Override
    @Transactional
    public AjaxResult deleteApiPermission(RoleEntity roleEntity, ApiEntity apiEntity) {
        this.roleApiRepository.deleteByRoleIdAndApiId(roleEntity.getRoleId(),apiEntity.getApiId());
        return new AjaxResult(true,"删除成功");
    }

    @Override
    @Transactional
    public AjaxResult deleteApiPermissions(Integer roleId, Integer[] componentIds) {
        this.roleApiRepository.deleteByRoleIdAndApiIdIsIn(roleId,componentIds);
        return new AjaxResult(true,"删除成功");
    }

    @Override
    public Iterable<String> getAllPermissionsByRoleId(Integer roleId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllPermissionsByRoleId'");
    }

    @Override
    public AjaxResult deleteAllPermissionsByRoleId(Integer roleId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteAllPermissionsByRoleId'");
    }
}
