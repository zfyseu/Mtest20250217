package org.example.service;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

@Component
public class DemoClient {
    @DubboReference(timeout = 5000)
    private IDemoService demoService;

    public void testDubbo() {
        demoService.hello();
    }
}
