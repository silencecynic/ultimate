//package com.api.boot.modules.configuration;
//
//import com.fasterxml.jackson.databind.JsonSerializer;
//import org.apache.kafka.clients.consumer.ConsumerConfig;
//import org.apache.kafka.clients.producer.ProducerConfig;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.annotation.EnableKafka;
//import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
//import org.springframework.kafka.core.*;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@EnableKafka
//@Configuration
//public class Kafka {
//
//
//    @Value("${spring.kafka.bootstrap-servers}")
//    private String bootstrap;
//
//
//    @Bean
//    public ConsumerFactory<String,Object> consumerFactory() {
//        Map<String,Object> config = new HashMap<>();
//        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrap);
//        config.put(ConsumerConfig.GROUP_ID_CONFIG,"kafka");
//        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, JsonSerializer.class);
//        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,JsonSerializer.class);
//        return new DefaultKafkaConsumerFactory<>(config);
//    }
//
//
//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String,Object> concurrentKafkaListenerContainerFactory() {
//        ConcurrentKafkaListenerContainerFactory<String,Object> concurrentKafkaListenerContainer = new ConcurrentKafkaListenerContainerFactory<>();
//        concurrentKafkaListenerContainer.setConsumerFactory(consumerFactory());
//        return concurrentKafkaListenerContainer;
//
//    }
//
//    @Bean
//    public ProducerFactory<String,Object> producerFactory () {
//        Map<String,Object> config = new HashMap<>();
//        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrap);
//        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
//        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,JsonSerializer.class);
//        return new DefaultKafkaProducerFactory<>(config);
//    }
//
//    @Bean
//    public KafkaTemplate<String,Object> kafkaTemplate() {
//        return new KafkaTemplate<>(producerFactory());
//    }
//
//
//
//}
