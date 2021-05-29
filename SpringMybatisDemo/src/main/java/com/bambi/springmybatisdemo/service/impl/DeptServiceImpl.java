package com.bambi.springmybatisdemo.service.impl;


import com.bambi.springmybatisdemo.mapper.DeptMapper;
import com.bambi.springmybatisdemo.service.IDeptService;
import com.bambi.springmybatisdemo.vo.DeptVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DeptServiceImpl implements IDeptService {

    @Resource
    private DeptMapper deptMapper;

    @Override
    public List<DeptVo> get() {
        return deptMapper.get();
    }
}
