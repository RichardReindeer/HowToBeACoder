package com.bambi.springboot.shiro.service.impl;

import com.bambi.springboot.shiro.mapper.UserMapper;
import com.bambi.springboot.shiro.pojo.User;
import com.bambi.springboot.shiro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public User queryUserByName(String username) {
        return userMapper.queryUserByName(username);
    }
}
