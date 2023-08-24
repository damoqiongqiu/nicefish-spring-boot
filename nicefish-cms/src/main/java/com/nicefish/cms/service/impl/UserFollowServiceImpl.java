package com.nicefish.cms.service.impl;

import com.nicefish.cms.jpa.entity.UserFollowEntity;
import com.nicefish.cms.jpa.repository.IUserFollowRepository;
import com.nicefish.cms.service.IUserFollowService;
import com.nicefish.rbac.jpa.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserFollowServiceImpl implements IUserFollowService {

    private final IUserFollowRepository userFollowRepository;

    @Autowired
    public UserFollowServiceImpl(IUserFollowRepository userFollowRepository) {
        this.userFollowRepository = userFollowRepository;
    }

    @Override
    public List<UserEntity> findFollowersByUserId(Integer userId) {
        return userFollowRepository.findFollowersByUserId(userId);
    }

    @Override
    public Long countFollowersByUserId(Integer userId) {
        return userFollowRepository.countFollowersByUserId(userId);
    }

    @Override
    public Long countFollowingByUserId(Integer userId) {
        return userFollowRepository.countFollowingByUserId(userId);
    }

    @Override
    @Transactional
    public UserFollowEntity createFollowRelation(UserFollowEntity userFollowEntity) {
        //TODO:这里的删除有 bug，没有删除成功
        userFollowRepository.delete(userFollowEntity);
        return userFollowRepository.save(userFollowEntity);
    }

    @Override
    @Transactional
    public void deleteFollowRelation(UserFollowEntity userFollowEntity) {
        UserFollowEntity temp = userFollowRepository.findByFromIdAndToId(
                userFollowEntity.getFromId(),
                userFollowEntity.getToId()
        );

        if (temp != null) {
            userFollowRepository.delete(temp);
        }
    }

    @Override
    public boolean existsFollow(UserFollowEntity userFollowEntity) {
        return userFollowRepository.existsByFromIdAndToId(
                userFollowEntity.getFromId(),
                userFollowEntity.getToId()
        );
    }
}
