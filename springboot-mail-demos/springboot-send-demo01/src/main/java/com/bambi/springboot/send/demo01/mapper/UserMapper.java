package com.bambi.springboot.send.demo01.mapper;

import com.bambi.springboot.send.demo01.vo.UserVo;
import io.swagger.annotations.ApiModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Mapper
@Component
@ApiModel("这是mybatis用来查询的接口")
public interface UserMapper {

    public UserVo selectUserByUsername(String username);
}
