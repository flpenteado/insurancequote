package io.acme.insurancequote.application.repository;

import io.acme.insurancequote.domain.models.Quotation;

import java.util.Optional;

public interface QuotationRepository {
    Quotation save(Quotation quotation);
    Optional<Quotation> findById(long id);
}
