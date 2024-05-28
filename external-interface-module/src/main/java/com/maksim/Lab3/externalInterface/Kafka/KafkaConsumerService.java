//package com.maksim.Lab3.externalInterface.Kafka;
//
//import Dtos.CatDto;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Service;
//
//@Service
//public class KafkaConsumerService {
//    private final ObjectMapper objectMapper;
//
//    public KafkaConsumerService(ObjectMapper objectMapper) {
//        this.objectMapper = objectMapper;
//    }
//
//    @KafkaListener(topics = "cat-topic", groupId = "your-consumer-group")
//    public void listen(String message) {
//        System.out.println("Received Message: " + message);
//    }
//
//    @KafkaListener(topics = "cat-topic", groupId = "your-consumer-group")
//    public void listenCat(String message) {
//        try {
//            CatDto catDTO = objectMapper.readValue(message, CatDto.class);
//            // Process the catDTO
//            System.out.println("Received Cat Message: " + catDTO);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//    }
//}