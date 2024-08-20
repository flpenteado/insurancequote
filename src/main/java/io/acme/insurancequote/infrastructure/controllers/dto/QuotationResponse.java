package io.acme.insurancequote.infrastructure.controllers.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.acme.insurancequote.domain.enums.QuotationCategory;
import io.acme.insurancequote.domain.models.Coverage;
import io.acme.insurancequote.domain.models.Quotation;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public record QuotationResponse(
        Long id,

        @JsonProperty("insurance_policy_id")
        Long insurancePolicyId,

        @JsonProperty("product_id")
        UUID productId,

        @JsonProperty("offer_id")
        UUID offerId,

        QuotationCategory category,

        @JsonProperty("created_at")
        LocalDateTime createdAt,

        @JsonProperty("updated_at")
        String updatedAt,

        @JsonProperty("total_monthly_premium_amount")
        Double totalMonthlyPremiumAmount,

        @JsonProperty("total_coverage_amount")
        Double totalCoverageAmount,

        Map<String, Double> coverages,

        List<String> assistances,

        CustomerResponse customer
) {

    public static QuotationResponse fromDomain(Quotation quotation) {
        Map<String, Double> coverages = new HashMap<>();

        for (Coverage coverage : quotation.getCoverages()) {
            coverages.put(coverage.getName(), coverage.getAmount().doubleValue());
        }

        return new QuotationResponse(
                quotation.getId(),
                quotation.getInsurancePolicyId(),
                quotation.getProductId(),
                quotation.getOfferId(),
                quotation.getCategory(),
                quotation.getCreatedAt(),
                quotation.getUpdatedAt().toString(),
                quotation.getTotalMonthlyPremiumAmount().doubleValue(),
                quotation.getTotalCoverageAmount().doubleValue(),
                coverages,
                quotation.getAssistances(),
                quotation.getCustomer() != null ? CustomerResponse.fromDomain(quotation.getCustomer()) : null
        );
    }
}
