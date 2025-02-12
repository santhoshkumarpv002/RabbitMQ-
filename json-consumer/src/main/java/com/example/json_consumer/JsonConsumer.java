
package com.example.json_consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
@Slf4j
@Service

public class JsonConsumer {

    @RabbitListener(queues = "${spring.rabbitmq.json.queue.name}")
    public void jsonConsumer(Order order) {

        log.info("JsonConusmer got ->" + order.toString());

    }

}