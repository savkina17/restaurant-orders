package com.example.restaurant.controller;

import com.example.restaurant.model.Order;
import com.example.restaurant.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public List<Order> all() {
        return orderService.findAll();
    }

    @PostMapping
    public Order create(@RequestBody Order order) {
        return orderService.create(order);
    }
}