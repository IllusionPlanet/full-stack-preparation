package com.illusionplanet.full_stack_preparation.controller;

import com.illusionplanet.full_stack_preparation.entity.SiteUser;
import com.illusionplanet.full_stack_preparation.service.ISiteUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/")
public class SiteUserController {
    @Autowired
    public ISiteUserService iSiteUserService;

    @PostMapping("sign-up")
    public String createSiteUser(@RequestBody SiteUser newSiteUser) {
        boolean isSuccessful = iSiteUserService.save(newSiteUser);
        return isSuccessful ? "Creation Successful" : "Creation Error";
    }

    @GetMapping("search-members")
    public List<SiteUser> retriveSiteUser() {
        return iSiteUserService.list();
    }
}
