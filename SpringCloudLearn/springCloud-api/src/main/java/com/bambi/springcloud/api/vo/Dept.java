package com.bambi.springcloud.api.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 一个实体类
 *
 * @author Bambi
 */


//javaBean规范，序列化！！！不然传输会出现错误
@Data
@NoArgsConstructor
//允许链式函数
@Accessors(chain = true)
public class Dept implements Serializable {
    //orm 对象关系映射，mysql的类对应的类，   类表关系映射
    //主键
    private Long deptNo;
    private String deptName;
    //查看这个数据是存在在哪个数据库的字段    因为微服务的特点，一个服务对应一个数据库,可能存在同一个信息可能存在在不同的数据库中
    private String dataSource;

    public Dept(String deptName) {
        this.deptName = deptName;
    }
}
