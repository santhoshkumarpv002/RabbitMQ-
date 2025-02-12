package com.example.rabbitmq_producer.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqProducerConfig {

    // Following three beans are autoconfigured by Spring Boot:
    // - RabbitTemplate
    // - RabbitMqAdmin
    // - ConnectionFactory

    @Value("${spring.rabbitmq.queue.name}")
    private String queueName; // Name of the queue

    @Value("${spring.rabbitmq.bindingKey.name}")
    private String bindingKey; // Binding key for the queue

    @Value("${spring.rabbitmq.exchange.name}")
    private String exchange; // Exchange name

    @Value("${spring.rabbitmq.json.queue.name}")
    private String jsonQueueName; // Name of the queue for JSON data

    @Value("${spring.rabbitmq.json.bindingKey.name}")
    private String jsonBindingKey; // Binding key for the JSON queue

    // Define the TopicExchange bean
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(exchange);
    }

    // Define the Queue bean
    @Bean
    public Queue queue() {
        return new Queue(queueName);
    }

    // Define the Binding bean for the queue
    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue())
                             .to(exchange())
                             .with(bindingKey);
    }

    // Define the Queue bean for JSON data
    @Bean
    public Queue jsonQueue() {
        return new Queue(jsonQueueName);
    }

    // Define the Binding bean for the JSON queue
    @Bean
    public Binding jsonBinding() {
        return BindingBuilder.bind(jsonQueue())
                             .to(exchange())
                             .with(jsonBindingKey);
    }

    // Define the MessageConverter bean to support JSON messages
    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    // Define the AmqpTemplate bean with JSON message converter
    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
}
