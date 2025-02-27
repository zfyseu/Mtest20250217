package org.example;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
@EnableDubbo
@SpringBootApplication(exclude = { KafkaAutoConfiguration.class })
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
