package com.bambi.spring.swagger.vo;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

//给实体类添加文档注释
@ApiModel("给实体类添加文档注释")
public class User {

    @ApiModelProperty("给属性添加文档注释")
    private String username;
    @ApiModelProperty("给属性添加注释")
    private String password;

}
