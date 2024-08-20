package io.acme.insurancequote.infrastructure.repository.mapper;

import io.acme.insurancequote.domain.models.Quotation;
import io.acme.insurancequote.infrastructure.repository.entity.QuotationEntity;

public class QuotationMapper {
    public static QuotationEntity toEntity(Quotation quotation) {
        QuotationEntity entity = new QuotationEntity();

        if (quotation.getId() > 0) {
            entity.setId(quotation.getId());
        }

        entity.setProductId(quotation.getProductId());
        entity.setOfferId(quotation.getOfferId());
        entity.setCategory(quotation.getCategory());
        entity.setTotalMonthlyPremiumAmount(quotation.getTotalMonthlyPremiumAmount());
        entity.setTotalCoverageAmount(quotation.getTotalCoverageAmount());
        entity.setCoverages(quotation.getCoverages());
        entity.setAssistances(quotation.getAssistances());
        entity.setCustomer(quotation.getCustomer());

        return entity;
    }

    public static Quotation toDomain(QuotationEntity entity) {
        var quotation =  new Quotation(
                entity.getProductId(),
                entity.getOfferId(),
                entity.getCategory(),
                entity.getTotalMonthlyPremiumAmount(),
                entity.getTotalCoverageAmount(),
                entity.getCoverages(),
                entity.getAssistances(),
                entity.getCustomer()
        );

        quotation.setId(entity.getId());
        quotation.setCreatedAt(entity.getCreatedAt());
        quotation.setUpdatedAt(entity.getUpdatedAt());

        return quotation;
    }
}
