package com.example.eventnotificator.repository;

import com.example.eventnotificator.entity.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {
    @Query("""
        SELECT n.id FROM NotificationEntity n
        WHERE n.userId= :id
        AND n.isRead=FALSE
    """)
    List<Long> findNotificationWithFalseIsRead(@Param("id") Long userId);

    @Modifying
    @Transactional
    @Query(value = """
    DELETE FROM "event-manage".notification n WHERE n.date < CURRENT_TIMESTAMP - INTERVAL '7 days'
    """, nativeQuery = true)
    void deleteOldNotifications();
}
