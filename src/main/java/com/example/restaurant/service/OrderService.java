package com.example.restaurant.service;

import com.example.restaurant.model.Order;
import com.example.restaurant.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final StringRedisTemplate redisTemplate;

    public Order create(Order order) {
        order.setStatus("NEW");
        order.setCreatedAt(LocalDateTime.now());
        Order saved = orderRepository.save(order);
        redisTemplate.opsForList().leftPush("kitchen:queue", "Order #" + saved.getId());
        return saved;
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }
}