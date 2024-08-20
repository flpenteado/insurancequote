package io.acme.insurancequote.application.usecase;

import io.acme.insurancequote.application.gateway.CatalogGateway;
import io.acme.insurancequote.application.messaging.QuotationMessage;
import io.acme.insurancequote.application.repository.QuotationRepository;
import io.acme.insurancequote.domain.models.Offer;
import io.acme.insurancequote.domain.models.Product;
import io.acme.insurancequote.domain.models.Quotation;
import io.acme.insurancequote.domain.service.QuotationValidator;

public class CreateQuotationUseCase {
    private final CatalogGateway catalogGateway;
    private final QuotationRepository quotationRepository;
    private final QuotationMessage quotationMessage;
    private final QuotationValidator validator = new QuotationValidator();

    public CreateQuotationUseCase(CatalogGateway catalogGateway,
                                  QuotationRepository quotationRepository,
                                  QuotationMessage quotationMessage
    ) {
        this.catalogGateway = catalogGateway;
        this.quotationRepository = quotationRepository;
        this.quotationMessage = quotationMessage;

    }

    public Quotation execute(Quotation quotation) {
        Product product = catalogGateway.getProductById(quotation.getProductId());
        Offer offer = catalogGateway.getOfferById(quotation.getOfferId());

        validator.validate(quotation, product, offer);

        var createdQuotation =  quotationRepository.save(quotation);

        quotationMessage.send(createdQuotation);

        return createdQuotation;
    }
}
