package com.bambi.springcloud.provider.serivce;

import com.bambi.demo.springcloud.vo.Dept;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IDeptService {

    public Dept getDeptByNo(Long id);

    public List<Dept> list();

    public boolean add(Dept dept);
}
