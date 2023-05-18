package com.justahmed99.worker.service.impl;

import com.justahmed99.worker.service.WebClientService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class WebClientServiceImpl implements WebClientService {
    @Value("${mediastack.uri}")
    private String apiUri;

    @Value("${mediastack.api-key}")
    private String apiKey;

    @Value("${mediastack.countries}")
    private String countries;

    @Value("${mediastack.limit}")
    private String limit;

    private final WebClient webClient;

    public WebClientServiceImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Mono<ResponseEntity<String>> sendRequest(String date) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("access_key", apiKey)
                        .queryParam("countries", countries)
                        .queryParam("limit", limit)
                        .queryParam("date", date)
                        .build())
                .retrieve()
                .toEntity(String.class);
    }
}
