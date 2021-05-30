package com.bambi.springcloud.provider.hystrix.service.impl;

import com.bambi.springcloud.api.vo.Dept;
import com.bambi.springcloud.provider.hystrix.mapper.DeptMapper;
import com.bambi.springcloud.provider.hystrix.service.IDeptService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DeptServiceImpl implements IDeptService {
    @Resource
    private DeptMapper deptMapper;

    @Override
    public boolean addDept(Dept dept) {

        return deptMapper.addDept(dept);
    }

    @Override
    public Dept queryDeptById(long id) {
        return deptMapper.queryDeptById(id);
    }

    @Override
    public List<Dept> queryAll() {
        return deptMapper.queryAll();
    }
}
