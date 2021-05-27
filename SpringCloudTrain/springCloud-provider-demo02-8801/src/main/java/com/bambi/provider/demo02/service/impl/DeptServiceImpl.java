package com.bambi.provider.demo02.service.impl;

import com.bambi.provider.demo02.mapper.DeptMapper;
import com.bambi.provider.demo02.service.IDeptService;
import com.bambi.springcloud.api.vo.Dept;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

@Service
public class DeptServiceImpl implements IDeptService, Serializable {

    @Resource
    private DeptMapper deptMapper;

    @Override
    public Dept getDeptById(Long id) {
        return deptMapper.getDeptById(id);
    }

    @Override
    public List<Dept> getAll() {
        return deptMapper.getAll();
    }

    @Override
    public boolean insertDept(Dept dept) {
        return deptMapper.insertDept(dept);
    }
}
