package com.bambi.springboot.shiro.demo05.mapper;

import com.bambi.springboot.shiro.demo05.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {
    //根据用户名查询用户信息

    public UserVo selectUserByUsername(String username);
}
