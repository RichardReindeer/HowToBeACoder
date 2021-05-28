package com.bambi.springcloud.provider.service;

import com.bambi.springcloud.api.vo.Dept;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IDeptService {

    /**
     * 添加部门
     * @param dept  这个部门是通过pom从另一个项目中拿到的
     * @return  添加成功与否
     */
    public boolean addDept(Dept dept);

    /**
     * 根据id查询部门
     * @param id
     * @return
     */
    public Dept queryDeptById(long id);

    /**
     * 查询所有部门
     * @return
     */
    public List<Dept> queryAll();
}
