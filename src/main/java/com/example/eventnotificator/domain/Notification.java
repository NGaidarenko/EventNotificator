package com.example.eventnotificator.domain;

import com.example.eventnotificator.dto.EventChangeKafkaMessage;

import java.time.LocalDateTime;

public record Notification (
        Long id,
        Long userId,
        LocalDateTime date,
        EventChangeKafkaMessage eventChangeKafkaMessage,
        Boolean isRead
) {
}
