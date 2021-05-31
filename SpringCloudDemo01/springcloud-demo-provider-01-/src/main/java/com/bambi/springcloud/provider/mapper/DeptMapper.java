package com.bambi.springcloud.provider.mapper;

import com.bambi.demo.springcloud.vo.Dept;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface DeptMapper {

    public Dept getDeptByNo(Long id);

    public List<Dept> list();

    public boolean add(Dept dept);
}
