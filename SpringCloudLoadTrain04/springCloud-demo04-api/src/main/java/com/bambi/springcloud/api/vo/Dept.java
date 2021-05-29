package com.bambi.springcloud.api.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 创建Vo值对象
 */
@Data
@NoArgsConstructor
public class Dept implements Serializable {
    private Long deptNo;
    private String deptName;
    private String dbSource;

    public Dept(String deptName) {
        this.deptName = deptName;
    }
}
