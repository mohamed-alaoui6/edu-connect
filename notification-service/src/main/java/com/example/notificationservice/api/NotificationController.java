package com.example.notificationservice.api;

import com.example.notificationservice.serviceconsommer.KafkaConsumerService;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final KafkaConsumerService kafkaConsumerService;

    // Injection du service
    public NotificationController(KafkaConsumerService kafkaConsumerService) {
        this.kafkaConsumerService = kafkaConsumerService;
    }

    // Récupérer toutes les notifications
    @GetMapping
    public ResponseEntity<List<String>> getNotifications() {
        return ResponseEntity.ok(kafkaConsumerService.getNotifications());
    }

    // Supprimer une notification par index
    @DeleteMapping("/{index}")
    public ResponseEntity<Void> deleteNotification(@PathVariable int index) {
        kafkaConsumerService.deleteNotification(index);
        return ResponseEntity.noContent().build();
    }

    // Vider toutes les notifications
    @DeleteMapping("/clear")
    public ResponseEntity<Void> clearNotifications() {
        kafkaConsumerService.clearNotifications();
        return ResponseEntity.noContent().build();
    }
}