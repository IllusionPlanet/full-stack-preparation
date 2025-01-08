package com.illusionplanet.full_stack_preparation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.illusionplanet.full_stack_preparation.common.AppException;
import com.illusionplanet.full_stack_preparation.common.Result;
import com.illusionplanet.full_stack_preparation.common.StatusEnum;
import com.illusionplanet.full_stack_preparation.entity.SiteUser;
import com.illusionplanet.full_stack_preparation.jwt.JwtInfo;
import com.illusionplanet.full_stack_preparation.jwt.JwtUtils;
import com.illusionplanet.full_stack_preparation.mapper.SiteUserMapper;
import com.illusionplanet.full_stack_preparation.service.ISiteUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SiteUserServiceImpl extends ServiceImpl<SiteUserMapper, SiteUser> implements ISiteUserService {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Value("${jwt.expireTime}")
    private int EXPIRE_TIME;

    @Override
    public Result createSiteUser(SiteUser newSiteUser) {
        if (this.save(newSiteUser)) {
            return Result.ok().customMessage("Creation successful");
        } else {
            // 假设创建失败原因是用户已存在
            return Result.setResult(StatusEnum.USER_EXISTS);
        }
    }

    @Override
//    @Cacheable(value = "index", key = "'listSiteUsers'")
    public Result listSiteUsers() {
        List<SiteUser> siteUserList = this.list();
        return Result.ok().customData("item", siteUserList);
    }

    @Override
    public Result saveToRedis(SiteUser newSiteUser) {
        try {
            redisTemplate.opsForValue().set(newSiteUser.getUsername(), newSiteUser.getPassword());
            return Result.ok();
        } catch (Exception exception) {
            exception.printStackTrace();
            return Result.setResult(StatusEnum.UNKNOWN_ERROR);
        }
    }

    @Override
    public Result login(SiteUser loginVO) {
        String username = loginVO.getUsername();
        String password = loginVO.getPassword();

        // 确认对应用户是否存在
        QueryWrapper<SiteUser> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        SiteUser foundUser = baseMapper.selectOne(wrapper);
        if (foundUser == null) {
            throw new AppException(StatusEnum.USER_NOT_FOUND);
        }
        // 如果用户存在，检查密码是否匹配
        if (!password.equals(foundUser.getPassword())) {
            throw new AppException(StatusEnum.WRONG_PASSWORD);
        }
        // 校验完毕，生成JWT
        JwtInfo jwtInfo = new JwtInfo();
        jwtInfo.setUsername(username);
        String jwtToken = JwtUtils.getJwtToken(jwtInfo, EXPIRE_TIME);
        return Result.ok().customData("token", jwtToken);
    }
}
