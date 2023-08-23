package com.nicefish.search.controller;

import com.nicefish.search.entity.UserSearchEntity;
import com.nicefish.search.service.IUserSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/nicefish/search/user")
public class UserSearchController {

    @Autowired
    private IUserSearchService userService;

    @RequestMapping("/getAll")
    public Iterable<UserSearchEntity> getAll() {
        return userService.getAll();
    }
}