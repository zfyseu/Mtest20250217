package org.example.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class KafkaConsumer {
    @KafkaListener(topics = "quickstart-events", containerFactory = "kafkaListenerContainerFactory")
    public void onMessageSync(ConsumerRecord<String, String> record, @Headers Map<String,Object> headers) {
        System.out.println("获取到的信息为：," + record.value());
    }

}
