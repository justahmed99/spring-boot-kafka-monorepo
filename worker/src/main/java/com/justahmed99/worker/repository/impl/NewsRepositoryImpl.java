package com.justahmed99.worker.repository.impl;

import com.justahmed99.worker.repository.NewsRepository;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Repository
public class NewsRepositoryImpl implements NewsRepository {
    private final ReactiveRedisOperations<String, Object> redisOperations;

    public NewsRepositoryImpl(
            ReactiveRedisOperations<String, Object> redisOperations
    ) {
        this.redisOperations = redisOperations;
    }

    @Override
    public Mono<Boolean> saveNews(String date, Object newsObject) {
        Duration ttl = Duration.ofHours(1);
        return redisOperations.opsForValue().set(date, newsObject, ttl);
    }
}
