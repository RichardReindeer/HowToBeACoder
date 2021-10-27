package com.bambi.mybatisdemo02;

import com.bambi.mybatisdemo02.dao.User;
import com.bambi.mybatisdemo02.mapper.IProductMapper;
import com.bambi.mybatisdemo02.mapper.IUserMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * 描述：
 *
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID     DATE          PERSON          REASON
 *  1      2021/10/26 6:29    Bambi        Create
 * ****************************************************************************
 * </pre>
 *
 * @author Bambi
 * @since 1.0
 */
@SpringBootTest
public class QueryWrapperDemo {
    @Autowired
    private IUserMapper userMapper;
    @Autowired
    private IProductMapper productMapper;


    @Test
    public void demoWrapper(){
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.gt("age",12);
        List<User> users = userMapper.selectList(userQueryWrapper);
        users.forEach(user -> {
            System.out.println(user.getUsername());
        });
    }
}
