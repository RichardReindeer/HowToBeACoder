package com.bambi.springboot.shiro.demo04.service.impl;

import com.bambi.springboot.shiro.demo04.mapper.UserMapper;
import com.bambi.springboot.shiro.demo04.pojo.User;
import com.bambi.springboot.shiro.demo04.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;
    @Override
    public User selectUserByUsername(String username) {
        return userMapper.selectUserByUsername(username);
    }
}
