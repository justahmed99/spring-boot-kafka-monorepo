package com.justahmed99.userapi.service.impl;

import com.justahmed99.userapi.dto.request.MessageRequest;
import com.justahmed99.userapi.repository.NewsRepository;
import com.justahmed99.userapi.service.MessageService;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class MessageServiceImpl implements MessageService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final NewsRepository newsRepository;

    public MessageServiceImpl(
            KafkaTemplate<String, String> kafkaTemplate,
            NewsRepository newsRepository
    ) {
        this.kafkaTemplate = kafkaTemplate;
        this.newsRepository = newsRepository;
    }

    @Override
    public Mono<Boolean> publish(MessageRequest request) {
        ProducerRecord<String, String> record = new ProducerRecord<>("news", null, request.getMessage());
        return Mono.fromFuture(kafkaTemplate.send(record))
                .map(result -> result.getRecordMetadata().hasOffset());
    }

    @Override
    public Mono<Object> getNews(String date) {
        return newsRepository.getNews(date);
    }
}
