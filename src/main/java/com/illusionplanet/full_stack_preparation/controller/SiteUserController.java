package com.illusionplanet.full_stack_preparation.controller;

import com.illusionplanet.full_stack_preparation.common.Result;
import com.illusionplanet.full_stack_preparation.entity.SiteUser;
import com.illusionplanet.full_stack_preparation.service.ISiteUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/")
public class SiteUserController {
    private static final Logger log = LoggerFactory.getLogger(SiteUserController.class);
    @Autowired
    public ISiteUserService iSiteUserService;

    @PostMapping("sign-up")
    public Result createSiteUser(@RequestBody SiteUser newSiteUser) {
        return iSiteUserService.createSiteUser(newSiteUser);
    }

    @GetMapping("search-members")
    public Result listSiteUsers() {
        return iSiteUserService.listSiteUsers();
    }

    @PostMapping("save-to-redis")
    public Result saveToRedis(@RequestBody SiteUser newSiteUser) {
        return iSiteUserService.saveToRedis(newSiteUser);
    }

    @PostMapping("login")
    public Result login(@RequestBody SiteUser loginVO) {
        return iSiteUserService.login(loginVO);
    }
}
