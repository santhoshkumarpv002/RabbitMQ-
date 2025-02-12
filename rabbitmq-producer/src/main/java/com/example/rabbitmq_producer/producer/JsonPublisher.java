package com.example.rabbitmq_producer.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.rabbitmq_producer.dto.Order;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JsonPublisher {

    private final RabbitTemplate rabbitTemplate;

    @Value("${spring.rabbitmq.exchange.name}")
    private String exchange;

    @Value("${spring.rabbitmq.json.bindingKey.name}")
    private String jsonBindingKey;

    public JsonPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void postJson(Order order) {
        log.info("JsonData sent -> " + order.toString());
        rabbitTemplate.convertAndSend(exchange, jsonBindingKey, order);
    }
}
