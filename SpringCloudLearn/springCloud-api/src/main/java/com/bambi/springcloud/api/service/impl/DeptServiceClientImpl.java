package com.bambi.springcloud.api.service.impl;

import com.bambi.springcloud.api.service.IDeptServiceClient;
import com.bambi.springcloud.api.vo.Dept;

import java.util.List;

public class DeptServiceClientImpl implements IDeptServiceClient {
    @Override
    public Dept queryById(Long id) {
        return new Dept().setDeptName("这是一个服务降级测试预警，服务被关闭了，大哥");
    }

    @Override
    public List<Dept> queryAll() {
        return null;
    }

    @Override
    public boolean addDept(Dept dept) {
        return false;
    }
}
