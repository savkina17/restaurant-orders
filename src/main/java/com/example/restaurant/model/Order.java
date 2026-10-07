package com.example.restaurant.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dishName;

    private Integer quantity;

    private String status; // NEW, COOKING, DONE

    private LocalDateTime createdAt;
}