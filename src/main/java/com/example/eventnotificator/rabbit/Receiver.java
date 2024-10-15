package com.example.eventnotificator.rabbit;

import com.example.eventnotificator.dto.EventChangeKafkaMessage;
import com.example.eventnotificator.service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Receiver {
    static final String queueName = "firstQueue";
    static final String exchangeName = "testExchange";
    private static final Logger log = LoggerFactory.getLogger(Receiver.class);

    @RabbitListener(queues = queueName)
    public void listen(EventChangeKafkaMessage message) {
        log.info("EvenChangesKafkaMessage: " + message);
    }
}
