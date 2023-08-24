package com.nicefish.search.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.nicefish.search.entity.PostSearchEntity;
import com.nicefish.search.service.IPostSearchService;
import jakarta.json.Json;
import org.apache.tomcat.util.json.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectSerializer;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/nicefish/search/post")
public class PostSearchController {
    private static final Logger logger = LoggerFactory.getLogger(PostSearchController.class);

    @Autowired
    private IPostSearchService postSearchService;

    @RequestMapping("/get-all")
    public Iterable<PostSearchEntity> getAll() {
        return postSearchService.getAll();
    }

    @GetMapping("/q/{page}/{size}/{keyword}")
    public Page<PostSearchEntity> searchSimilar(@PathVariable String keyword, @PathVariable int page, @PathVariable int size) {
        PostSearchEntity entity = new PostSearchEntity();
        entity.setTitle(keyword);
        entity.setContent(keyword);
        entity.setSummary(keyword);
        return postSearchService.searchSimilar(entity,page-1,size);
    }
}