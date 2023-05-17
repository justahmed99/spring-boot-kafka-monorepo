package com.justahmed99.userapi.controller;

import com.justahmed99.userapi.dto.request.MessageRequest;
import com.justahmed99.userapi.dto.response.DataResponse;
import com.justahmed99.userapi.service.MessageService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/message")
public class MessageController {
    private final MessageService service;
    public MessageController(
            MessageService service
    ) {
        this.service = service;
    }

    @PostMapping("")
    public Mono<DataResponse<Object>> publishMessage(@RequestBody MessageRequest request) {
        return service.publish(request)
                .flatMap(status -> Mono.just(new DataResponse<>("message sent", true, null)))
                .onErrorResume(throwable -> Mono.just(new DataResponse<>("message send failed", false, null)));
    }
}
