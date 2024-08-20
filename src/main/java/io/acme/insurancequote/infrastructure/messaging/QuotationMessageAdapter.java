package io.acme.insurancequote.infrastructure.messaging;

import io.acme.insurancequote.application.messaging.QuotationMessage;
import io.acme.insurancequote.application.messaging.dto.PolicyIssued;
import io.acme.insurancequote.domain.models.Quotation;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.Payload;

import java.util.function.Consumer;

public class QuotationMessageAdapter implements QuotationMessage {

    @Value("${app.rabbit.quotation.queue}")
    private String quotationQueue;

    private final RabbitTemplate rabbitTemplate;

    private final Consumer<PolicyIssued> policyIssuedConsumer;

    public QuotationMessageAdapter(RabbitTemplate rabbitTemplate, Consumer<PolicyIssued> policyIssuedConsumer) {
        this.rabbitTemplate = rabbitTemplate;
        this.policyIssuedConsumer = policyIssuedConsumer;
    }

    @Override
    public void send(Quotation message) {
        rabbitTemplate.convertAndSend(quotationQueue, message);
    }

    @Override
    @RabbitListener(queues = "${app.rabbit.policy.queue}")
    public void receive(@Payload PolicyIssued message) {
        try {
            policyIssuedConsumer.accept(message);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

}
