package com.bambi.springcloud.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 符合javaBean规范，必须实现序列化接口 ,为了以后RPC或HTTP模式调用时进行序列化与反序列化
 */
@Data
@NoArgsConstructor
public class UserVo implements Serializable {
    private Long userId;
    private String userName;
    private String permission;

    public UserVo(String userName) {
        this.userName = userName;
    }
}
