package com.example.rabbitmq_producer.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RabbitmqPublisher {

    
    @Value("${spring.rabbitmq.exchange.name}")
    private String exchange;

    @Value("${spring.rabbitmq.queue.name}")
    private String queneName;

    @Value("${spring.rabbitmq.bindingKey.name}")
    private String bindingKey;

    @Autowired
    private RabbitTemplate rabbitTemplate;

//post msg
    public void publishMessage(String msg){
        log.info("Message posted -> "+msg);
        rabbitTemplate.convertAndSend(exchange, bindingKey, msg);
}


//post json data

}