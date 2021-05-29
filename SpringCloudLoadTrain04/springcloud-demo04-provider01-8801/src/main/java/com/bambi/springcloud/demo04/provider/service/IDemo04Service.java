package com.bambi.springcloud.demo04.provider.service;

import com.bambi.springcloud.api.vo.Dept;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IDemo04Service {

    public Dept getDeptById(Long id);

    public List<Dept> getList();

    public boolean add(Dept dept);
}
