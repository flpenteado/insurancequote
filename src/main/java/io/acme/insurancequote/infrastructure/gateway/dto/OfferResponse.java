package io.acme.insurancequote.infrastructure.gateway.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.acme.insurancequote.domain.models.Coverage;
import io.acme.insurancequote.domain.models.MonthlyPremiumAmount;
import io.acme.insurancequote.domain.models.Offer;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@Data
@AllArgsConstructor
public class OfferResponse {
    private UUID id;

    @JsonProperty("product_id")
    private UUID productId;

    private String name;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    private boolean active;

    private Map<String, Double> coverages;

    private String[] assistances;

    @JsonProperty("monthly_premium_amount")
    private PremiumValues monthlyPremiumAmount;

    public Offer toOffer() {
        List<Coverage> coverages = this.coverages.entrySet().stream()
                .map(entry -> new Coverage(entry.getKey(), BigDecimal.valueOf(entry.getValue())))
                .toList();

        return new Offer(
                id,
                productId,
                name,
                createdAt,
                active,
                coverages,
                List.of(assistances),
                new MonthlyPremiumAmount(
                        BigDecimal.valueOf(monthlyPremiumAmount.minAmount),
                        BigDecimal.valueOf(monthlyPremiumAmount.maxAmount),
                        BigDecimal.valueOf(monthlyPremiumAmount.suggestedAmount)
                )
        );
    }

    @Data
    @AllArgsConstructor
    public static class PremiumValues {
        @JsonProperty("max_amount")
        private Double maxAmount;

        @JsonProperty("min_amount")
        private Double minAmount;

        @JsonProperty("suggested_amount")
        private Double suggestedAmount;
    }
}
