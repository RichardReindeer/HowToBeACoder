package com.bambi.springboot.send.demo01.service.impl;


import com.bambi.springboot.send.demo01.mapper.UserMapper;
import com.bambi.springboot.send.demo01.service.IUserService;
import com.bambi.springboot.send.demo01.vo.UserVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements IUserService {

    @Resource
    UserMapper userMapper;

    @Override
    public UserVo selectUserByUsername(String username) {
        return userMapper.selectUserByUsername(username);
    }
}
