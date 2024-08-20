package io.acme.insurancequote.domain.models;

import io.acme.insurancequote.domain.enums.QuotationCategory;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class Quotation {
    private long id;
    private UUID productId;
    private UUID offerId;
    private long insurancePolicyId;
    private QuotationCategory category;
    private BigDecimal totalMonthlyPremiumAmount;
    private BigDecimal totalCoverageAmount;
    private List<Coverage> coverages;
    private List<String> assistances;
    private Customer customer;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Quotation(
            UUID productId,
            UUID offerId,
            QuotationCategory category,
            BigDecimal totalMonthlyPremiumAmount,
            BigDecimal totalCoverageAmount,
            List<Coverage> coverages,
            List<String> assistances,
            Customer customer

    ) {
        this.productId = productId;
        this.offerId = offerId;
        this.category = category;
        this.totalMonthlyPremiumAmount = totalMonthlyPremiumAmount;
        this.totalCoverageAmount = totalCoverageAmount;
        this.coverages = coverages;
        this.assistances = assistances;
        this.customer = customer;
    }

    public BigDecimal getCoverageAmountSum() {
        return coverages.stream()
                .map(Coverage::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
