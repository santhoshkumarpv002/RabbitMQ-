package com.example.rabbitmq_consumer1;


import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;


import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MsgConsumer {
    
@RabbitListener(queues = "${spring.rabbitmq.queue.name}")
public void getmsg(String msg){
log.info("Consumer-1 got ->"+msg);

}

}
