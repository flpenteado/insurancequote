package io.acme.insurancequote.application.usecase;

import io.acme.insurancequote.application.repository.QuotationRepository;
import io.acme.insurancequote.domain.models.Quotation;

import java.util.Optional;

public class GetQuotationUseCase {

    private final QuotationRepository quotationRepository;

    public GetQuotationUseCase(QuotationRepository quotationRepository) {
        this.quotationRepository = quotationRepository;
    }

    public Optional<Quotation> getQuotationById(long id) {
        return quotationRepository.findById(id);
    }
}
