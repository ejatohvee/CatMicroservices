//package com.maksim.Lab3.externalInterface.Configs;
//
//import org.apache.kafka.common.serialization.StringSerializer;
//import org.apache.kafka.clients.producer.ProducerConfig;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
//import org.springframework.kafka.core.DefaultKafkaProducerFactory;
//import org.springframework.kafka.core.ProducerFactory;
//import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
//import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
//import org.springframework.kafka.support.serializer.JsonSerializer;
//
//import java.util.HashMap;
//import java.util.Map;
//
//
////@Configuration
////public class KafkaConfig {
////    @Value("${com.maksim.reply-topics}")
////    private String REPLY_TOPICS;
////    @Bean
////    public ReplyingKafkaTemplate<String, Object, Object> replyingTemplate(
////            ProducerFactory<String, Object> pf,
////            ConcurrentMessageListenerContainer<String, Object> repliesContainer) {
////
////        return new ReplyingKafkaTemplate<>(pf, repliesContainer);
////    }
////
////    @Bean
////    public ConcurrentMessageListenerContainer<String, Object> repliesContainer(
////            ConcurrentKafkaListenerContainerFactory<String, Object> containerFactory) {
////
////        ConcurrentMessageListenerContainer<String, Object> repliesContainer = containerFactory.createContainer(REPLY_TOPICS);
////        repliesContainer.setAutoStartup(false);
////        return repliesContainer;
////    }
////
////    @Bean
////    public ProducerFactory<String, Object> objectProducerFactory() {
////        Map<String, Object> configProps = new HashMap<>();
////        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
////        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
////        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
////        return new DefaultKafkaProducerFactory<>(configProps);
////    }
////
////    @Bean
////    public KafkaTemplate<String, String> kafkaTemplate() {
////        return new KafkaTemplate<>(producerFactory());
////    }
//
//
////    @Bean
////    public ConsumerFactory<String, String> consumerFactory() {
////        Map<String, Object> props = new HashMap<>();
////        props.put("bootstrap.servers", "localhost:9092");
////        props.put("group.id", "your-consumer-group");
////        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
////        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
////        return new DefaultKafkaConsumerFactory<>(props);
////    }
////
////    @Bean
////    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
////        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
////        factory.setConsumerFactory(consumerFactory());
////        return factory;
////    }
//}
