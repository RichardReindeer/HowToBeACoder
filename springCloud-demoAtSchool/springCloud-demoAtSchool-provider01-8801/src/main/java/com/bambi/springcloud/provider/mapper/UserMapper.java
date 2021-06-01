package com.bambi.springcloud.provider.mapper;

import com.bambi.springcloud.vo.UserVo;
import org.apache.catalina.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserMapper {

    public UserVo getUserVo(Long userId);

    public List<UserVo> getList();

    public String getUsersPermission(Long userId);

    public boolean addUser(UserVo userVo);
}
