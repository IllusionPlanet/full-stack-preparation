package com.illusionplanet.full_stack_preparation.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.illusionplanet.full_stack_preparation.common.Result;
import com.illusionplanet.full_stack_preparation.entity.SiteUser;

public interface ISiteUserService extends IService<SiteUser> {
    Result createSiteUser(SiteUser newSiteUser);

    Result listSiteUsers();

    Result saveToRedis(SiteUser newSiteUser);

    Result login(SiteUser loginVO);
}
