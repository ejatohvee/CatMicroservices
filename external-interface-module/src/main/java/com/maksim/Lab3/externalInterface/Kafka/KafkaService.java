package com.maksim.Lab3.externalInterface.Kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaService {

    private final KafkaTemplate<String, Object> kafka;

    public void sendSynchronously(Object request, String topic) {
        kafka.send(topic, request);
    }
}