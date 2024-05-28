//package com.maksim.Lab3.externalInterface.Kafka;
//
//import Dtos.CatDto;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Service;
//
//@Service
//public class KafkaProducerService {
//
//    private final KafkaTemplate<String, String> kafkaTemplate;
//    private final ObjectMapper objectMapper;
//
//    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
//        this.kafkaTemplate = kafkaTemplate;
//        this.objectMapper = objectMapper;
//    }
//
//    public void sendMessage(String topic, CatDto catDto) {
//        try {
//            String message = objectMapper.writeValueAsString(catDto);
//            kafkaTemplate.send(topic, message);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//    }
//}