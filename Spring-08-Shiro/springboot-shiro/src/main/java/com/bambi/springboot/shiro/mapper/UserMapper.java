package com.bambi.springboot.shiro.mapper;

import com.bambi.springboot.shiro.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {
    public User queryUserByName(String username);
}
