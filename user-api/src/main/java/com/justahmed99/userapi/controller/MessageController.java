package com.justahmed99.userapi.controller;

import com.justahmed99.userapi.dto.request.MessageRequest;
import com.justahmed99.userapi.dto.response.DataResponse;
import com.justahmed99.userapi.service.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("")
    public Mono<ResponseEntity<DataResponse<Object>>> getNews(@RequestParam(name = "date") String date) {
        return service.getNews(date)
                .flatMap(data -> Mono.just(
                        ResponseEntity.status(HttpStatus.OK)
                                .body(new DataResponse<>
                                        ("data found", true, data))))
                .switchIfEmpty(Mono.defer(() -> Mono.just(
                        ResponseEntity.status(HttpStatus.NOT_FOUND).
                                body(new DataResponse<>
                                        ("data not found, sending request to broker", false, null)))));
    }
}
