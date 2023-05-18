package com.justahmed99.worker.service;

import reactor.core.publisher.Mono;

import java.net.http.HttpResponse;

public interface WebClientService {
    Mono<HttpResponse> sendRequest(String date);
}
