package com.nicefish.cms.service.impl;

import com.nicefish.cms.jpa.entity.UserPostRelationEntity;
import com.nicefish.cms.jpa.repository.IUserPostRelationRepository;
import com.nicefish.cms.service.IUserPostRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserPostRelationServiceImpl implements IUserPostRelationService {

    private final IUserPostRelationRepository userPostRelationRepository;

    @Autowired
    public UserPostRelationServiceImpl(IUserPostRelationRepository userPostRelationRepository) {
        this.userPostRelationRepository = userPostRelationRepository;
    }

    @Override
    public void saveUserPostRelation(UserPostRelationEntity userPostRelation) {
        userPostRelationRepository.save(userPostRelation);
    }

    @Override
    public UserPostRelationEntity findByUserIdAndPostId(Integer userId, Integer postId) {
        return userPostRelationRepository.findByUserIdAndPostId(userId, postId);
    }

    @Override
    public List<UserPostRelationEntity> findAllByUserId(Integer userId) {
        return userPostRelationRepository.findAllByUserId(userId);
    }

    @Override
    public List<UserPostRelationEntity> findAllByPostId(Integer postId) {
        return userPostRelationRepository.findAllByPostId(postId);
    }

    @Override
    public void deleteUserPostRelation(UserPostRelationEntity userPostRelation) {
        userPostRelationRepository.delete(userPostRelation);
    }

    @Override
    public int countByPostIdAndRelationType(Integer postId, Integer relationType) {
        return userPostRelationRepository.countByPostIdAndRelationType(postId, relationType);
    }

    @Override
    public int countLikesForUserPosts(Integer userId) {
        return userPostRelationRepository.countLikesForUserPosts(userId);
    }
}
