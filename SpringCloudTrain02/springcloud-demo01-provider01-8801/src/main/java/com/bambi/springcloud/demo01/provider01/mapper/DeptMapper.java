package com.bambi.springcloud.demo01.provider01.mapper;

import com.bambi.springcloud.demo01.api.vo.Dept;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface DeptMapper {

    public Dept getDeptById(Long id);

    public List<Dept> getAll();

    public boolean add(Dept dept);
}
