package com.example.demo.config;

import com.example.demo.domain.port.primary.CallHandler;
import com.example.demo.domain.port.secondary.CallCenterRepository;
import com.example.demo.domain.port.secondary.Logger;
import com.example.demo.infra.adapter.secondary.InMemoryCallCenterRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public Logger logger() {
        return new com.example.demo.infra.adapter.secondary.Logger();
    }

    @Bean
    public CallCenterRepository callCenterRepository() {
        return new InMemoryCallCenterRepository();
    }

    @Bean
    public CallHandler callHandler(CallCenterRepository callCenterRepository, Logger logger) {
        return new CallHandler(logger, callCenterRepository);
    }
}
