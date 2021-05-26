package com.bambi.springcloud.provider.demo.service.impl;

import com.bambi.spring.api.pojo.Dept;
import com.bambi.springcloud.provider.demo.mapper.DeptMapper;
import com.bambi.springcloud.provider.demo.service.IDeptService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DeptServiceImpl implements IDeptService {

    @Resource
    private DeptMapper deptMapper;

    @Override
    public Dept selectDeptById(Long id) {
        return deptMapper.selectDeptById(id);
    }

    @Override
    public List<Dept> selectAll() {
        return deptMapper.selectAll();
    }

    @Override
    public boolean addDept(Dept dept) {
        return deptMapper.addDept(dept);
    }
}
