package org.example.config;

import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;

@Configuration
@EnableKafka
public class KafkaConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.kafka.radius.producer")
    public KafkaProperties.Producer radiusKafkaProducerConfig(){
        return new KafkaProperties.Producer();
    }

    @Bean
    public ProducerFactory<String, String> producerFactory() {
        return new DefaultKafkaProducerFactory<>(radiusKafkaProducerConfig().buildProperties());
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.kafka.radius.consumer")
    public KafkaProperties.Consumer radiusKafkaConsumerConfig(){
        return new KafkaProperties.Consumer();
    }

    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(radiusKafkaConsumerConfig().buildProperties());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        // 其他容器配置，如并发数...
        factory.setConcurrency(1); // 设置并发数为1
        return factory;
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
