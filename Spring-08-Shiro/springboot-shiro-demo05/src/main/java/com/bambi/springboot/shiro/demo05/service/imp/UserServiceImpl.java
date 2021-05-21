package com.bambi.springboot.shiro.demo05.service.imp;


import com.bambi.springboot.shiro.demo05.mapper.UserMapper;
import com.bambi.springboot.shiro.demo05.service.IUserService;
import com.bambi.springboot.shiro.demo05.vo.UserVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements IUserService {

    @Resource
    UserMapper userMapper;

    @Override
    public UserVo selectUserByUsername(String username) {
        UserVo userVo = userMapper.selectUserByUsername(username);
        return userVo;
    }
}
