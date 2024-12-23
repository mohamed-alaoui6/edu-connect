package com.example.notificationservice.serviceconsommer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KafkaConsumerService {

    // Stockage des notifications en mémoire
    private final List<String> notifications = new ArrayList<>();

    // Consommer les messages Kafka
    @KafkaListener(topics = "course-topic", groupId = "notification-group")
    public void consumeMessage(String message) {
        System.out.println("Notification reçue : " + message);

        // Ajouter le message reçu dans la liste
        notifications.add(message);

        // Simuler l'envoi de notification
        sendNotification(message);
    }

    private void sendNotification(String message) {
        System.out.println("Envoi de notification : " + message);
    }

    // Méthodes pour accéder aux notifications
    public List<String> getNotifications() {
        return notifications;
    }

    public void clearNotifications() {
        notifications.clear();
    }

    public void deleteNotification(int index) {
        if (index >= 0 && index < notifications.size()) {
            notifications.remove(index);
        }
    }
}
