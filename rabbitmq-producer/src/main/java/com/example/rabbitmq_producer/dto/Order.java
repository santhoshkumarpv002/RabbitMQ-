package com.example.rabbitmq_producer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order {
    
    private int orderId;
    private String name;
    private long quantity;
    private double price;

}
