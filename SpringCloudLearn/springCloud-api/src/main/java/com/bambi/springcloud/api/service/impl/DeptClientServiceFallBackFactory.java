package com.bambi.springcloud.api.service.impl;

import com.bambi.springcloud.api.service.IDeptServiceClient;
import com.bambi.springcloud.api.vo.Dept;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 实现feign负载均衡接口，处理在调用服务失败时的回调
 * 用来服务降级
 */
@Component
public class DeptClientServiceFallBackFactory implements FallbackFactory {

    @Override
    public IDeptServiceClient create(Throwable cause) {
        return new IDeptServiceClient() {
            @Override
            public Dept queryById(Long id) {
                return new Dept().setDeptName("Not found recv by Hystrix;cause cant find useful message,客户端启动了降级操作，该服务已经被关闭")
                        .setDeptNo(id)
                        .setDataSource("No DBSource");
            }

            @Override
            public List<Dept> queryAll() {
                return null;
            }

            @Override
            public boolean addDept(Dept dept) {
                return false;
            }
        };
    }
}
