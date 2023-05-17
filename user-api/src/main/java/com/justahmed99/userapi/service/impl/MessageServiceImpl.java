package com.justahmed99.userapi.service.impl;

import com.justahmed99.userapi.dto.request.MessageRequest;
import com.justahmed99.userapi.service.MessageService;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import reactor.core.publisher.Mono;

@Service
public class MessageServiceImpl implements MessageService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public MessageServiceImpl(
            KafkaTemplate<String, String> kafkaTemplate
    ) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public Mono<Boolean> publish(MessageRequest request) {
        ProducerRecord<String, String> record = new ProducerRecord<>("news", null, request.getMessage());
        return Mono.fromFuture(kafkaTemplate.send(record).completable())
                .map(result -> result.getRecordMetadata().hasOffset());
    }
}
