package org.example.controller;

import org.example.service.DemoClient;
import org.example.service.KafkaService;
import org.example.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/v1/test")
@RestController
public class TestController {
    @Autowired
    private RedisService redisService;
    @Autowired
    private KafkaService kafkaService;
    @Autowired
    private DemoClient demoClient;

    @GetMapping("testRedis")
    public void testRedis() throws Exception {
        // redisService.test1();
        redisService.testLock();
    }

    @GetMapping("testKafka")
    public void testKafka() {
        kafkaService.sendMessage();
    }

    @GetMapping("testDubbo")
    public void testDubbo() {
        demoClient.testDubbo();
    }
}
