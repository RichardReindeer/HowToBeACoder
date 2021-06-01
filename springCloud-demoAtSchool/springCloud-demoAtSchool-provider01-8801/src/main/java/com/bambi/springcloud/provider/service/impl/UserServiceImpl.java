package com.bambi.springcloud.provider.service.impl;

import com.bambi.springcloud.provider.mapper.UserMapper;
import com.bambi.springcloud.provider.service.IUserSerivce;
import com.bambi.springcloud.vo.UserVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements IUserSerivce {

    @Resource
    private UserMapper userMapper;

    @Override
    public UserVo getUserVo(Long userId) {
        return userMapper.getUserVo(userId);
    }

    @Override
    public List<UserVo> getList() {
        return userMapper.getList();
    }

    @Override
    public String getUsersPermission(Long userId) {
        if (userId!=null){
            return userMapper.getUsersPermission(userId);
        }
        return null;
    }

    @Override
    public boolean addUser(UserVo userVo) {
        return userMapper.addUser(userVo);
    }
}
