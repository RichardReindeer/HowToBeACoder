package com.bambi.springmybatisdemo.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class DeptVo implements Serializable {

    private Long deptId;
    private String deptName;
    private String dbSource;

    public DeptVo(String deptName) {
        this.deptName = deptName;
    }
}
