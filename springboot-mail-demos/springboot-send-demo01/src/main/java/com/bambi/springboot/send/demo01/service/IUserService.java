package com.bambi.springboot.send.demo01.service;

import com.bambi.springboot.send.demo01.mapper.UserMapper;
import com.bambi.springboot.send.demo01.vo.UserVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public interface IUserService {

    public UserVo selectUserByUsername(String username);
}
