package com.example.eventnotificator.controller;

import com.example.eventnotificator.service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {
    private static final Logger log = LoggerFactory.getLogger(NotificationController.class);

    @Autowired
    private NotificationService notificationService;

    @PostMapping("/{notificationId}")
    public ResponseEntity<Void> readNotification(@PathVariable Long notificationId) {
        log.info("Reading notifications with id: {}", notificationId);
        notificationService.readNotification(notificationId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<Long>> getAllNotification() {
        log.info("Getting all notifications");
        List<Long> allNotification = notificationService.getAllNotification();

        return new ResponseEntity<>(allNotification, HttpStatus.OK);
    }
}
