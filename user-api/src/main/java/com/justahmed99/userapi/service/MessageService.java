package com.justahmed99.userapi.service;

import com.justahmed99.userapi.dto.request.MessageRequest;
import reactor.core.publisher.Mono;

public interface MessageService {
    Mono<Boolean> publish(MessageRequest request);
}
