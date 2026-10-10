package com.example.restaurant.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String KITCHEN_QUEUE = "kitchen.queue";

    @Bean
    public Queue kitchenQueue() {
        return new Queue(KITCHEN_QUEUE, true);
    }
}