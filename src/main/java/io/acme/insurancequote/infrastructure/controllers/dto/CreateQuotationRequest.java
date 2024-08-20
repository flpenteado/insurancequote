package io.acme.insurancequote.infrastructure.controllers.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.acme.insurancequote.domain.enums.QuotationCategory;
import io.acme.insurancequote.domain.models.Coverage;
import io.acme.insurancequote.domain.models.Quotation;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Data
@AllArgsConstructor
public class CreateQuotationRequest{
    @JsonProperty("product_id")
    private UUID productId;

    @JsonProperty("offer_id")
    private UUID offerId;

    @JsonProperty("category")
    @Enumerated(EnumType.STRING)
    private QuotationCategory category;

    @JsonProperty("total_monthly_premium_amount")
    private Double totalMonthlyPremiumAmount;

    @JsonProperty("total_coverage_amount")
    private Double totalCoverageAmount;

    @JsonProperty("coverages")
    private Map<String, Double> coverages;

    @JsonProperty("assistances")
    private List<String> assistances;

    @JsonProperty("customer")
    private CustomerResponse customer;

    public Quotation toDomain() {
        return new Quotation(
                productId,
                offerId,
                category,
                BigDecimal.valueOf(totalMonthlyPremiumAmount),
                BigDecimal.valueOf(totalCoverageAmount),
                coverages.entrySet().stream()
                        .map(entry -> new Coverage(entry.getKey(), BigDecimal.valueOf(entry.getValue())))
                        .toList(),
                assistances,
                customer.toDomain()
        );
    }
}
