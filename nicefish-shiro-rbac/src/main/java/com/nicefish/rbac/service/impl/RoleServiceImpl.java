package com.nicefish.rbac.service.impl;

import com.nicefish.rbac.jpa.entity.*;
import com.nicefish.rbac.jpa.repository.IPermissionRepository;
import com.nicefish.rbac.jpa.repository.IRolePermissionRepository;
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
    private IRolePermissionRepository rolePermissionRepository;

    @Autowired
    private IPermissionRepository permissionRepository;

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
    public int deleteRole(Long roleId) {
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
        Long[] longIds=new Long[roleIds.length];
        for (int i = 0; i < roleIds.length; i++) {
            longIds[i]=Long.parseLong(roleIds[i]);
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

        this.rolePermissionRepository.deleteAllByRoleId(oldEntity.getRoleId());

        List<PermissionEntity> permissionEntities=oldEntity.getPermissionEntities();
        if(!CollectionUtils.isEmpty(permissionEntities)){
            List<RolePermissionEntity> rolePermissionEntities=new ArrayList<RolePermissionEntity>();
            for (PermissionEntity permissionEntity : permissionEntities) {
                RolePermissionEntity rolePermissionEntity=new RolePermissionEntity();
                rolePermissionEntity.setRoleId(oldEntity.getRoleId());
                rolePermissionEntity.setPermissionId(permissionEntity.getPermissionId());
                rolePermissionEntities.add(rolePermissionEntity);
            }
            this.rolePermissionRepository.saveAll(rolePermissionEntities);
        }
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
    public RoleEntity getRoleById(Long roleId) {
        return this.roleRepository.findDistinctByRoleId(roleId);
    }

    @Override
    public Page<UserEntity> getAuthUsersByRoleId(Long roleId, Pageable pageable) {
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
    public AjaxResult deleteAuthUsers(Long roleId) {
        this.userRoleRepository.deleteAllByRoleId(roleId);
        return new AjaxResult(true,"删除成功");
    }

    @Override
    @Transactional
    public AjaxResult deleteAuthUsers(Long roleId, Long[] userIds) {
        this.userRoleRepository.deleteByRoleIdAndUserIdIsIn(roleId,userIds);
        return new AjaxResult(true,"删除成功");
    }

    @Override
    @Transactional
    public AjaxResult addAuthUsers(Long roleId, Long[] userIds) {
        List<UserRoleEntity> userRoleEntityList=new ArrayList<>();
        for(int i=0;i<userIds.length;i++){
            UserRoleEntity userRoleEntity=new UserRoleEntity();
            userRoleEntity.setRoleId(roleId);
            userRoleEntity.setUserId(userIds[i]);
            userRoleEntityList.add(userRoleEntity);
        }
        this.userRoleRepository.saveAll(userRoleEntityList);
        return new AjaxResult(true,"保存成功");
    }

    @Override
    @Transactional
    public AjaxResult addAuthUsers(Long roleId, List<UserEntity> userEntities) {
        List<UserRoleEntity> userRoleEntityList=new ArrayList<>();
        userEntities.forEach(userEntity -> {
            UserRoleEntity userRoleEntity=new UserRoleEntity();
            userRoleEntity.setRoleId(roleId);
            userRoleEntity.setUserId(userEntity.getUserId());
            userRoleEntityList.add(userRoleEntity);
        });
        this.userRoleRepository.saveAll(userRoleEntityList);
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
    public Iterable<PermissionEntity> getAllPermissionsByRoleId(Long roleId) {
        Iterable<RoleEntity> roleEntities= this.roleRepository.findAllByRoleId(roleId);
        Iterable<PermissionEntity> permissionEntities=this.permissionRepository.findPermissionEntitiesByRoleEntitiesIn(roleEntities);
        return permissionEntities;
    }

    @Override
    @Transactional
    public AjaxResult addPermissions(Long roleId, Long[] permissionIds) {
        List<RolePermissionEntity> rolePermissionEntities=new ArrayList<>();
        for (int i = 0; i < permissionIds.length; i++) {
            RolePermissionEntity rolePermissionEntity=new RolePermissionEntity();
            rolePermissionEntity.setRoleId(roleId);
            rolePermissionEntity.setPermissionId(permissionIds[i]);
            rolePermissionEntities.add(rolePermissionEntity);
        }
        this.rolePermissionRepository.saveAll(rolePermissionEntities);
        return new AjaxResult(true,"保存成功");
    }

    @Override
    @Transactional
    public AjaxResult addPermissions(RoleEntity roleEntity, List<PermissionEntity> permissionEntities) {
        List<RolePermissionEntity> rolePermissionEntities=new ArrayList<>();
        permissionEntities.forEach(permissionEntity -> {
            RolePermissionEntity rolePermissionEntity=new RolePermissionEntity();
            rolePermissionEntity.setRoleId(roleEntity.getRoleId());
            rolePermissionEntity.setPermissionId(permissionEntity.getPermissionId());
            rolePermissionEntities.add(rolePermissionEntity);
        });
        this.rolePermissionRepository.saveAll(rolePermissionEntities);
        return new AjaxResult(true,"保存成功");
    }

    @Override
    @Transactional
    public AjaxResult deletePermissions(Long roleId, Long[] permissionIds) {
        this.rolePermissionRepository.deleteByRoleIdAndPermissionIdIsIn(roleId,permissionIds);
        return new AjaxResult(true,"删除成功");
    }

    @Override
    @Transactional
    public AjaxResult deleteAllPermissionsByRoleId(Long roleId) {
        this.rolePermissionRepository.deleteAllByRoleId(roleId);
        return new AjaxResult(true,"删除成功");
    }

    @Override
    @Transactional
    public AjaxResult deletePermission(Long roleId, Long permissionId) {
        this.rolePermissionRepository.deleteByRoleIdAndPermissionId(roleId,permissionId);
        return new AjaxResult(true,"删除成功");
    }
}
