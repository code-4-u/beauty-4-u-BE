package com.beauty4u.backend.config.mail;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsyncConfig {
    @Bean
    public Executor asyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);   // 기본 5개 동시 처리 스레드
        executor.setMaxPoolSize(10);   // 최대 10개 스레드
        executor.setQueueCapacity(25); // 대기열 25개
        executor.initialize();
        return executor;
    }
}
