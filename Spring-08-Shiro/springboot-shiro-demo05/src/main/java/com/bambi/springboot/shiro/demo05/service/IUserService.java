package com.bambi.springboot.shiro.demo05.service;

import com.bambi.springboot.shiro.demo05.vo.UserVo;
import org.springframework.stereotype.Service;


public interface IUserService {
    public UserVo selectUserByUsername(String username);
}
