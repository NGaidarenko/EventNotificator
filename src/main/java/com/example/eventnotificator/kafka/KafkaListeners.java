package com.example.eventnotificator.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {

    @KafkaListener(topics = "event-notification", groupId = "groupId")
    void listener(String data) {
        System.out.println("Received: " + data);
    }
}
