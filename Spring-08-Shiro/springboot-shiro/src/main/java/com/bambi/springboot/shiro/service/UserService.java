package com.bambi.springboot.shiro.service;

import com.bambi.springboot.shiro.pojo.User;

public interface UserService {

    public User queryUserByName(String username);
}
