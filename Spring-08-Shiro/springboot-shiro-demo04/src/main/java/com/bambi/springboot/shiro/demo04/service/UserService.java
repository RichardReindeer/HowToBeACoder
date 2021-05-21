package com.bambi.springboot.shiro.demo04.service;

import com.bambi.springboot.shiro.demo04.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public User selectUserByUsername(String username);
}
