package com.example.rabbitmq_producer.controller;

import org.springframework.web.bind.annotation.RestController;
import com.example.rabbitmq_producer.dto.Order;
import com.example.rabbitmq_producer.producer.JsonPublisher;
import com.example.rabbitmq_producer.producer.RabbitmqPublisher;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@RestController
@AllArgsConstructor
public class ProducerController {

    private final RabbitmqPublisher rabbitmqPublisher;
    private final JsonPublisher jsonPublisher;

    @PostMapping("/post")
    public String postMethodName(@RequestBody Order order) {
        jsonPublisher.postJson(order);
        return "order placed";
    }

    @GetMapping("/{msg}")
    public String getMethodName(@PathVariable String msg) {
        System.out.println("inside controller");
        
        rabbitmqPublisher.publishMessage(msg);
        return "msg published";
    }
}
