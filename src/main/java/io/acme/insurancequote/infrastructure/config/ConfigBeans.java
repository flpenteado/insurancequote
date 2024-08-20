package io.acme.insurancequote.infrastructure.config;

import io.acme.insurancequote.application.gateway.CatalogGateway;
import io.acme.insurancequote.application.repository.QuotationRepository;
import io.acme.insurancequote.application.usecase.CreateQuotationUseCase;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ConfigBeans {
    private final CatalogGateway catalogGateway;
    private final QuotationRepository quotationRepository;

    public ConfigBeans(CatalogGateway catalogGateway, QuotationRepository quotationRepository) {
        this.catalogGateway = catalogGateway;
        this.quotationRepository = quotationRepository;
    }

    @Bean
    @Primary
    public CatalogGateway catalogGateway() {
        return catalogGateway;
    }

    @Bean
    public CreateQuotationUseCase createQuotationUseCase() {
        return new CreateQuotationUseCase(catalogGateway, quotationRepository);
    }

}
