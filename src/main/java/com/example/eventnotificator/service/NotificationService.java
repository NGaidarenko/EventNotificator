package com.example.eventnotificator.service;

import com.example.eventnotificator.domain.Role;
import com.example.eventnotificator.domain.User;
import com.example.eventnotificator.dto.EventChangeKafkaMessage;
import com.example.eventnotificator.entity.EventFieldChangeEntity;
import com.example.eventnotificator.entity.NotificationEntity;
import com.example.eventnotificator.repository.NotificationRepository;
import com.example.eventnotificator.security.UserAuthenticationService;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class NotificationService {
    private static final Logger log = LoggerFactory.getLogger(NotificationService.class);

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private UserAuthenticationService authenticationService;

    public void createNotification(EventChangeKafkaMessage kafkaMessage) {
        log.info("Creating notification for {}", kafkaMessage);

        List<EventFieldChangeEntity> changes = Stream.of(
                        Optional.ofNullable(kafkaMessage.getName())
                                .map(elem -> new EventFieldChangeEntity(null, "name", elem.getOldField(), elem.getNewField())),
                        Optional.ofNullable(kafkaMessage.getMaxPlaces())
                                .map(elem -> new EventFieldChangeEntity(null, "maxPlaces", String.valueOf(elem.getOldField()), String.valueOf(elem.getNewField()))),
                        Optional.ofNullable(kafkaMessage.getDate())
                                .map(elem -> new EventFieldChangeEntity(null, "date", String.valueOf(elem.getOldField()), String.valueOf(elem.getNewField()))),
                        Optional.ofNullable(kafkaMessage.getCost())
                                .map(elem -> new EventFieldChangeEntity(null, "cost", String.valueOf(elem.getOldField()), String.valueOf(elem.getNewField()))),
                        Optional.ofNullable(kafkaMessage.getDuration())
                                .map(elem -> new EventFieldChangeEntity(null, "duration", String.valueOf(elem.getOldField()), String.valueOf(elem.getNewField()))),
                        Optional.ofNullable(kafkaMessage.getLocationId())
                                .map(elem -> new EventFieldChangeEntity(null, "locationId", String.valueOf(elem.getOldField()), String.valueOf(elem.getNewField()))),
                        Optional.ofNullable(kafkaMessage.getStatus())
                                .map(elem -> new EventFieldChangeEntity(null, "status", String.valueOf(elem.getOldField()), String.valueOf(elem.getNewField())))
                )
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());

        NotificationEntity notification = new NotificationEntity(
                null,
                kafkaMessage.getOwnerId(),
                LocalDateTime.now(),
                changes,
                false
        );

        notificationRepository.save(notification);
    }

    public void readNotification(Long notificationId) {
        log.info("Read notification with id: {}", notificationId);

        User user = authenticationService.getCurrentAuthenticatedUser();
        NotificationEntity notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new EntityNotFoundException("Notification with id: %s not founded".formatted(notificationId)));

        if (notification.getIsRead()) {
            throw new IllegalArgumentException("Notification already read");
        }

        if (!user.id().equals(notification.getUserId()) && !user.role().equals(Role.ADMIN)) {
            throw new IllegalArgumentException("Can not read notification, because you not owner");
        }


        notification.setIsRead(Boolean.TRUE);

        notificationRepository.save(notification);
    }

    public List<Long> getAllNotification() {
        log.info("Getting all notifications in service");
        User user = authenticationService.getCurrentAuthenticatedUser();

        return notificationRepository.findNotificationWithFalseIsRead(user.id());
    }

}
