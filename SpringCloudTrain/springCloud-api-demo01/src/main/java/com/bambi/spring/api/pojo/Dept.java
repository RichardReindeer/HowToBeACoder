package com.bambi.spring.api.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 实现序列化接口防止传输时出现问题
 */
@Data
@NoArgsConstructor
public class Dept implements Serializable {

    private Long deptId;
    private String deptName;
    private String dbSource;

    public Dept(String deptName) {
        this.deptName = deptName;
    }
}
