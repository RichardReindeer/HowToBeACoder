package com.bambi.springcloud.demo04.provider.service.impl;

import com.bambi.springcloud.api.vo.Dept;
import com.bambi.springcloud.demo04.provider.mapper.DeptMapper;
import com.bambi.springcloud.demo04.provider.service.IDemo04Service;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class Demo04ServiceImpl implements IDemo04Service {

    @Resource
    private DeptMapper deptMapper;

    @Override
    public Dept getDeptById(Long id) {
        return deptMapper.getDeptById(id);
    }

    @Override
    public List<Dept> getList() {
        return deptMapper.getList();
    }

    @Override
    public boolean add(Dept dept) {
        return deptMapper.add(dept);
    }
}
