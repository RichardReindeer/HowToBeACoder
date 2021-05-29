package com.bambi.springcloud.demo04.provider.mapper;

import com.bambi.springcloud.api.vo.Dept;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DeptMapper {

    public Dept getDeptById(Long id);

    public List<Dept> getList();

    public boolean add(Dept dept);
}
