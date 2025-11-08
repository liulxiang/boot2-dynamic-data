package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.User;

/**
 * User Service 接口
 */
public interface UserService extends IService<User> {

    /**
     * 从slave数据源获取用户
     */
    User getUserFromSlave(Long id);
}