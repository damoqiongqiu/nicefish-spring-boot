package com.nicefish.search.controller;

import com.nicefish.search.entity.PostSearchEntity;
import com.nicefish.search.entity.UserSearchEntity;
import com.nicefish.search.service.IUserSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/nicefish/search/user")
public class UserSearchController {

    @Autowired
    private IUserSearchService userSearchService;

    @RequestMapping("/get-all")
    public Iterable<UserSearchEntity> getAll() {
        return userSearchService.getAll();
    }

    @GetMapping("/q/{page}/{size}/{keyword}")
    public Page<UserSearchEntity> searchSimilar(@PathVariable String keyword, @PathVariable int page, @PathVariable int size) {
        UserSearchEntity entity = new UserSearchEntity();
        entity.setUserName(keyword);
        entity.setNickName(keyword);
        entity.setEmail(keyword);
        return userSearchService.searchSimilar(entity,page-1,size);
    }
}