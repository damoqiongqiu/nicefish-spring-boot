package com.nicefish.rbac.service.impl;


import com.nicefish.rbac.jpa.entity.PermissionEntity;
import com.nicefish.rbac.jpa.entity.RoleEntity;
import com.nicefish.rbac.jpa.repository.IPermissionRepository;
import com.nicefish.rbac.jpa.repository.IRoleRepository;
import com.nicefish.rbac.service.IPermissionService;
import com.nicefish.core.utils.AjaxResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
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
public class PermissionServiceImpl implements IPermissionService {
    @Autowired
    private IPermissionRepository permissionRepository;

    @Autowired
    private IRoleRepository roleRepository;

    @Override
    public PermissionEntity getPermissionDetail(Long permissionId) {
        return this.permissionRepository.findDistinctByPermissionId(permissionId);
    }

    /**
     * 获取本角色还没有分配的权限
     * @param roleEntity
     * @return
     */
    @Override
    public Iterable<PermissionEntity> getAllPermissionsNotIn(RoleEntity roleEntity) {
        RoleEntity tempEntity=this.roleRepository.findDistinctByRoleId(roleEntity.getRoleId());
        if(ObjectUtils.isEmpty(tempEntity)||
                CollectionUtils.isEmpty(tempEntity.getPermissionEntities())){
            return this.permissionRepository.findAll();
        }
        ArrayList<Long> ids=new ArrayList<>();
        for (PermissionEntity permissionEntity : tempEntity.getPermissionEntities()) {
            ids.add(permissionEntity.getPermissionId());
        }
        return this.permissionRepository.findPermissionEntitiesByPermissionIdNotIn(ids.toArray(new Long[ids.size()]));
    }

    @Override
    public Iterable<PermissionEntity> getAllPermissions() {
        return this.permissionRepository.findAll();
    }

    @Override
    public Page<PermissionEntity> getPermissionList(PermissionEntity permissionEntity, Pageable pageable) {
        return this.permissionRepository.findAll(new Specification<PermissionEntity>() {
            @Override
            public Predicate toPredicate(Root<PermissionEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates=new ArrayList<>();
                if(!StringUtils.isEmpty(permissionEntity.getPermissionStr())){
                    predicates.add(criteriaBuilder.like(root.get("permissionStr"),"%"+permissionEntity.getPermissionStr()+"%"));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        },pageable);
    }

    @Override
    @Transactional
    public AjaxResult createPermission(PermissionEntity permissionEntity) {
        if(!this.isPermissionStrUnique(permissionEntity)){
            return AjaxResult.failure("创建失败，权限字符串已存在");
        }
        this.permissionRepository.save(permissionEntity);
        return AjaxResult.success("保存成功");
    }

    @Override
    @Transactional
    public AjaxResult deletePermission(PermissionEntity permissionEntity) {
        //TODO:系统内置的权限定义不能删除
        //TODO:正在使用中的角色权限不能删除
        this.permissionRepository.delete(permissionEntity);
        return new AjaxResult(true,"删除成功");
    }

    @Override
    @Transactional
    public int deletePermission(Long permissionId) {
        PermissionEntity permissionEntity = this.permissionRepository.findDistinctByPermissionId(permissionId);
        //特权角色状态位-1，不准删除
        if(permissionEntity.getStatus()==-1){
            return 0;
        }
        return this.permissionRepository.deleteDistinctByPermissionId(permissionId);
    }

    @Override
    @Transactional
    public AjaxResult editPermission(PermissionEntity permissionEntity) {
        PermissionEntity oldEntity=this.permissionRepository.findDistinctByPermissionId(permissionEntity.getPermissionId());
        if(oldEntity.getStatus()==-1){
            return AjaxResult.failure("保存失败，内置权限不可修改");
        }
        this.permissionRepository.deleteDistinctByPermissionId(permissionEntity.getPermissionId());
        BeanUtils.copyProperties(permissionEntity,oldEntity);
        this.permissionRepository.save(oldEntity);
        return AjaxResult.success("保存成功");
    }

    @Override
    public boolean isPermissionStrUnique(PermissionEntity permissionEntity) {
        return this.permissionRepository.findDistinctByPermissionStr(permissionEntity.getPermissionStr())==null?true:false;
    }

    @Override
    public Page<RoleEntity> getRoleListByPermissionId(Long permissionId, Pageable pageable) {
        return this.permissionRepository.findRoleEntitiesByPermissionId(permissionId,pageable);
    }
}
