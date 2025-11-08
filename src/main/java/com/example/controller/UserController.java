package com.example.controller;

import com.example.entity.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * User Controller
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 添加用户（使用master数据源）
     */
    @PostMapping("/add")
    public boolean addUser(@RequestBody User user) {
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        return userService.save(user);
    }

    /**
     * 根据ID获取用户（使用slave数据源）
     */
    @GetMapping("/get/{id}")
    public User getUser(@PathVariable Long id) {
        // 调用UserService中的方法，该方法使用@DS("slave")注解
        return userService.getUserFromSlave(id);
    }

    /**
     * 获取所有用户（使用slave数据源）
     */
    @GetMapping("/list")
    public List<User> getAllUsers() {
        return userService.listAllUsersFromSlave();
    }
}