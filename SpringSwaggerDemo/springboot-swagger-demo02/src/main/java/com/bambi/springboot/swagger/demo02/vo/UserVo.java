package com.bambi.springboot.swagger.demo02.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 测试一下值对象的私有属性和公有属性的访问关系
 */
@ApiModel("这是一个User值对象")
public class UserVo {

    @ApiModelProperty("这是用户名")
    public String username;
    @ApiModelProperty("这是密码")
    private String password;

    public UserVo(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
