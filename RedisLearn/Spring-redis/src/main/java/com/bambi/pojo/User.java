package com.bambi.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
/**
 * 测试一下不序列化的特点
 */
//在企业中，我们的所有pojo都会序列化，JavaBean命名规范!!!!
public class User implements Serializable{

    private int id;
    private String name;
    private String permission;

}
