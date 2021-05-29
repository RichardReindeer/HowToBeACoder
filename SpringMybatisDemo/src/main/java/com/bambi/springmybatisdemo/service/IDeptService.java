package com.bambi.springmybatisdemo.service;

import com.bambi.springmybatisdemo.vo.DeptVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IDeptService {

    public List<DeptVo> get();
}
