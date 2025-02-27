package org.example.service;

import org.apache.dubbo.config.annotation.DubboService;

@DubboService(registry = "zk")
public class DemoServiceImpl implements IDemoService {
    @Override
    public void hello() {
        System.out.println("hello");
    }
}
