package io.acme.insurancequote.infrastructure.config;

import io.acme.insurancequote.application.gateway.CatalogGateway;
import io.acme.insurancequote.application.messaging.QuotationMessage;
import io.acme.insurancequote.application.repository.QuotationRepository;
import io.acme.insurancequote.application.usecase.CreateQuotationUseCase;
import io.acme.insurancequote.application.usecase.GetQuotationUseCase;
import io.acme.insurancequote.infrastructure.messaging.QuotationMessageAdapter;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ConfigBeans {
    private final CatalogGateway catalogGateway;
    private final QuotationRepository quotationRepository;
    private final RabbitTemplate rabbitTemplate;

    public ConfigBeans(CatalogGateway catalogGateway,
                       QuotationRepository quotationRepository,
                       RabbitTemplate rabbitTemplate
    ) {
        this.catalogGateway = catalogGateway;
        this.quotationRepository = quotationRepository;
        this.rabbitTemplate = rabbitTemplate;

    }

    @Bean
    @Primary
    public CatalogGateway catalogGateway() {
        return catalogGateway;
    }

    @Bean
    public CreateQuotationUseCase createQuotationUseCase() {
        return new CreateQuotationUseCase(
                catalogGateway,
                quotationRepository,
                quotationQueue());
    }

    @Bean
    public GetQuotationUseCase getQuotationUseCase() {
        return new GetQuotationUseCase(quotationRepository);
    }

    @Bean
    public QuotationMessage quotationQueue() {
        return new QuotationMessageAdapter(rabbitTemplate);
    }
}
