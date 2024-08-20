package io.acme.insurancequote.infrastructure.messaging;

import io.acme.insurancequote.application.messaging.QuotationMessage;
import io.acme.insurancequote.domain.models.Quotation;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;

public class QuotationMessageAdapter implements QuotationMessage {

    @Value("${app.rabbit.quotation.queue}")
    private String queueName;

    private final RabbitTemplate rabbitTemplate;

    public QuotationMessageAdapter(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void send(Quotation message) {
        rabbitTemplate.convertAndSend(queueName, message);
    }

}
