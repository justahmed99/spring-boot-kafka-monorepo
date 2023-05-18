package com.justahmed99.worker.service.impl;

import com.justahmed99.worker.service.WebClientService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.net.http.HttpResponse;

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
    public Mono<HttpResponse> sendRequest(String date) {
        URI uri = UriComponentsBuilder.fromUriString(apiUri)
                .queryParam("access_key", apiKey)
                .queryParam("countries", countries)
                .queryParam("limit", limit)
                .queryParam("date", date)
                .build()
                .toUri();
        return webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(HttpResponse.class);
    }
}
