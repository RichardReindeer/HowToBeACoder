package com.bambi.springmybatisdemo.mapper;

import com.bambi.springmybatisdemo.vo.DeptVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface DeptMapper {
    public List<DeptVo> get();
}
