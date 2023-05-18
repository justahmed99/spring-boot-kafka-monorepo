package com.justahmed99.worker.repository;

import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface NewsRepository {
    Mono<Boolean> saveNews(String date, Object newsObject);
}
