package com.illusionplanet.full_stack_preparation.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("site_user")
public class SiteUser extends BaseEntity{
    private String username;

    private String password;
}
