package io.acme.insurancequote.infrastructure.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@EnableRabbit
public class RabbitConfig {

    @Value("${app.rabbit.quotation.queue}")
    private String quotationQueue;

    @Value("${app.rabbit.policy.queue}")
    private String policyQueue;


    @Bean
    public org.springframework.amqp.core.Queue queue() {
        return new org.springframework.amqp.core.Queue(quotationQueue, true);
    }

    @Bean
    public org.springframework.amqp.core.Queue policyQueue() {
        return new org.springframework.amqp.core.Queue(policyQueue, true);
    }

    @Bean
    public Exchange exchange() {
        return ExchangeBuilder
                .directExchange(quotationQueue)
                .durable(true)
                .build();
    }

    @Bean
    public Binding binding() {
        return BindingBuilder
                .bind(queue())
                .to(exchange())
                .with(quotationQueue)
                .noargs();
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter(ObjectMapper objectMapper) {
        return new Jackson2JsonMessageConverter(objectMapper);
    }

    @Bean
    public SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();

        factory.setConnectionFactory(connectionFactory);
        factory.setMaxConcurrentConsumers(2);
        return factory;
    }
}
