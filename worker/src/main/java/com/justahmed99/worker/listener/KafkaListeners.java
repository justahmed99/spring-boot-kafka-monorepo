package com.justahmed99.worker.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {
    @KafkaListener(topics = "news", groupId = "message-group")
    void listener(String data) {
        System.out.printf("Listener received: %s%n", data);
    }
}
