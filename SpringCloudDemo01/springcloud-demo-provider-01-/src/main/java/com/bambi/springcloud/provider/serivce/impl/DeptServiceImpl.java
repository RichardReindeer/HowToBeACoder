package com.bambi.springcloud.provider.serivce.impl;

import com.bambi.demo.springcloud.vo.Dept;
import com.bambi.springcloud.provider.mapper.DeptMapper;
import com.bambi.springcloud.provider.serivce.IDeptService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DeptServiceImpl implements IDeptService {

    @Resource
    private DeptMapper deptMapper;

    @Override
    public Dept getDeptByNo(Long id) {
        return deptMapper.getDeptByNo(id);
    }

    @Override
    public List<Dept> list() {
        return deptMapper.list();
    }

    @Override
    public boolean add(Dept dept) {
        return deptMapper.add(dept);
    }
}
