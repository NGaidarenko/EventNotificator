package com.example.eventnotificator.dto;

import java.time.LocalDateTime;

public record NotificationDto (
        Long id,
        Long userId,
        LocalDateTime date,
        EventChangeKafkaMessage eventChangeKafkaMessage,
        Boolean isRead
) {
}
