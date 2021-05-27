package com.bambi.provider.demo02.service;

import com.bambi.springcloud.api.vo.Dept;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public interface IDeptService {
    //根据部门id查询部门信息
    public Dept getDeptById(Long id);

    //获取所有部门信息
    public List<Dept> getAll();

    //添加部门信息
    public boolean insertDept(Dept dept);
}
