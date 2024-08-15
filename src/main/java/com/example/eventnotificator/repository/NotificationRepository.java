package com.example.eventnotificator.repository;

import com.example.eventnotificator.dto.EventChangeKafkaMessage;
import com.example.eventnotificator.entity.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {
}
