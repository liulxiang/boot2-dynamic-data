package com.example.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.User;
import com.example.mapper.UserMapper;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * User Service 实现类
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 使用master数据源
     */
    @DS("master")
    @Override
    public boolean save(User entity) {
        return super.save(entity);
    }
    /**
     * 使用slave数据源
     */
    @DS("slave")
    public User getUserFromSlave(Long id) {
        return super.getById(id);
    }
}