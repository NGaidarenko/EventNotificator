package com.example.eventnotificator.scheduler;

import com.example.eventnotificator.repository.NotificationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
@Configuration
public class SchedulerConfig {

    private static final Logger log = LoggerFactory.getLogger(SchedulerConfig.class);

    @Autowired
    private NotificationRepository notificationRepository;

    @Scheduled(cron = "${event.status.cron}")
    public void deleteOldNotifications() {
        log.info("Deleting old notifications");
        notificationRepository.deleteOldNotifications();
    }
}
