package com.justahmed99.worker.listener;

import com.justahmed99.worker.service.WebClientService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.net.http.HttpResponse;

@Component
public class KafkaListeners {

    private final WebClientService webClientService;

    public KafkaListeners (
            WebClientService webClientService
    ) {
        this.webClientService = webClientService;
    }

    @KafkaListener(topics = "news", groupId = "message-group")
    void listener(String data) {
        System.out.printf("Listener received: %s%n", data);

        Mono<HttpResponse> response = webClientService.sendRequest(data);
        response.subscribe(
                successResponse -> {
                    System.out.println("Data request succeed");
                },
                error -> {
                    System.out.println("Data request failed");
                }
        );
    }
}
