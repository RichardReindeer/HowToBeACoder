package com.bambi.springcloud.provider.demo.service;

import com.bambi.spring.api.pojo.Dept;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 业务逻辑层
 */
@Service
public interface IDeptService {

    public Dept selectDeptById(Long id);

    public List<Dept> selectAll();

    public boolean addDept(Dept dept);
}
