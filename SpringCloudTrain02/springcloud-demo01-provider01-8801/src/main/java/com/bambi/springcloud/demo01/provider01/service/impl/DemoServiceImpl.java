package com.bambi.springcloud.demo01.provider01.service.impl;
import com.bambi.springcloud.demo01.api.vo.Dept;
import com.bambi.springcloud.demo01.provider01.mapper.DeptMapper;
import com.bambi.springcloud.demo01.provider01.service.IDemoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

@Service
public class DemoServiceImpl implements Serializable, IDemoService {

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
    public boolean add(Dept dept) {
        return deptMapper.add(dept);
    }
}
