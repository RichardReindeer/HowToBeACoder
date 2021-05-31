package com.bambi.demo.springcloud.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 这是一个ORM 对象关系映射
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
