package com.bambi.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("springCloud-demoAtSchool-provider")
public interface FeignService {


}
