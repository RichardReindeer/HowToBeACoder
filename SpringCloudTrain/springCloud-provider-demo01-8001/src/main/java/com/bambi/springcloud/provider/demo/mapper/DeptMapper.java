package com.bambi.springcloud.provider.demo.mapper;

import com.bambi.spring.api.pojo.Dept;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 数据访问层
 */
@Repository
@Mapper
public interface DeptMapper {

    /**
     * 根据id查询部门信息
     * @param id
     * @return api的pojo中的数据库对象映射
     */
    public Dept selectDeptById(Long id);

    public List<Dept> selectAll();

    public boolean addDept(Dept dept);
}
