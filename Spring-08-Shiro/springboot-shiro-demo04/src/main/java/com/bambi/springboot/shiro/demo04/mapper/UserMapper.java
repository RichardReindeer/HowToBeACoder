package com.bambi.springboot.shiro.demo04.mapper;

import com.bambi.springboot.shiro.demo04.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {

    public User selectUserByUsername(String username);
}
