package com.example.restaurant.service;

import com.example.restaurant.config.RabbitConfig;
import com.example.restaurant.model.Order;
import com.example.restaurant.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final RabbitTemplate rabbitTemplate;

    public Order create(Order order) {
        order.setStatus("NEW");
        order.setCreatedAt(LocalDateTime.now());
        Order saved = orderRepository.save(order);
        rabbitTemplate.convertAndSend(
                RabbitConfig.KITCHEN_QUEUE,
                "Order #" + saved.getId() + ": " + saved.getDishName()
        );
        return saved;
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }
}