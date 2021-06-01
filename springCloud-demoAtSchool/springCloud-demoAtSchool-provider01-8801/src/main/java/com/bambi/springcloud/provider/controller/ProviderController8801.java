package com.bambi.springcloud.provider.controller;

import com.bambi.springcloud.provider.service.IUserSerivce;
import com.bambi.springcloud.vo.UserVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@ApiModel("这是一个服务者的控制器类")
public class ProviderController8801 {

    @Resource
    private IUserSerivce userSerivce;

    @ApiModelProperty("这是获取所有用户信息的GetMapper")
    @GetMapping("/user/list")
    public List<UserVo> list(){
        return userSerivce.getList();
    }

    @ApiModelProperty("这是根据id获取当前用户权限的getMapper")
    @GetMapping("/user/getPermission/{userId}")
    public String getPermission(@PathVariable("userId") Long userId){
        return userSerivce.getUsersPermission(userId);
    }

    @ApiModelProperty("这是根据id获取当前用户信息的getMapper")
    @GetMapping("/user/getUser/{userId}")
    public UserVo getUser(@PathVariable("userId") Long userId){
        return userSerivce.getUserVo(userId);
    }

    @ApiModelProperty("这是添加用户信息的Mapper")
    @PostMapping("/user/add")
    public boolean addUserVo(@RequestBody UserVo userVo){
        return userSerivce.addUser(userVo);
    }
}
