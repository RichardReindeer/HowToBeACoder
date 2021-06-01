package com.bambi.springcloud.provider.service;

import com.bambi.springcloud.vo.UserVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IUserSerivce {

    public UserVo getUserVo(Long userId);

    public List<UserVo> getList();

    public String getUsersPermission(Long userId);

    public boolean addUser(UserVo userVo);
}
