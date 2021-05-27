package com.bambi.provider.demo02.mapper;

import com.bambi.springcloud.api.vo.Dept;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface DeptMapper {

    //根据部门id查询部门信息
    public Dept getDeptById(Long id);

    //获取所有部门信息
    public List<Dept> getAll();

    //添加部门信息
    public boolean insertDept(Dept dept);
}
