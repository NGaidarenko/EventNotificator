package com.example.eventnotificator.kafka;

import com.example.eventnotificator.dto.EventChangeKafkaMessage;
import com.example.eventnotificator.service.NotificationService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {
    private static final Logger log = LoggerFactory.getLogger(KafkaListeners.class);

    @Autowired
    private NotificationService notificationService;

    @KafkaListener(topics = "event-notification", groupId = "groupId", containerFactory = "kafkaListenerContainerFactory")
    public void listener(ConsumerRecord<String, EventChangeKafkaMessage> kafkaMessage) {
        log.info("Received value: {}", kafkaMessage);

        notificationService.createNotification(kafkaMessage.value());
    }
}
