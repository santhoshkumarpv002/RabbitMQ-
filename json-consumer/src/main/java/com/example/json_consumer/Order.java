package com.example.json_consumer;

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
