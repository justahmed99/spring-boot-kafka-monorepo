package com.justahmed99.userapi.repository;

import reactor.core.publisher.Mono;

public interface NewsRepository {
    Mono<Object> getNews(String date);
}
