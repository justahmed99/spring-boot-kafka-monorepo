package com.justahmed99.worker.service;

import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

public interface WebClientService {
    Mono<ResponseEntity<String>> sendRequest(String date);
}
