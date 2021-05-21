package com.bambi.springboot.shiro.demo05.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVo {
    private int id;
    private String username;
    private String password;
    private String permission;
}
