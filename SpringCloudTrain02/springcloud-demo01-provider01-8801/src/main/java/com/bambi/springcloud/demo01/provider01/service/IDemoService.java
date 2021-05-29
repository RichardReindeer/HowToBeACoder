package com.bambi.springcloud.demo01.provider01.service;

import com.bambi.springcloud.demo01.api.vo.Dept;

import java.util.List;

public interface IDemoService {

    public Dept getDeptById(Long id);

    public List<Dept> getAll();

    public boolean add(Dept dept);
}
