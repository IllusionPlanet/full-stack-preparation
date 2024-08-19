package com.illusionplanet.full_stack_preparation.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.illusionplanet.full_stack_preparation.entity.SiteUser;
import com.illusionplanet.full_stack_preparation.mapper.SiteUserMapper;
import com.illusionplanet.full_stack_preparation.service.ISiteUserService;
import org.springframework.stereotype.Service;

@Service
public class SiteUserServiceImpl extends ServiceImpl<SiteUserMapper, SiteUser> implements ISiteUserService {
}
