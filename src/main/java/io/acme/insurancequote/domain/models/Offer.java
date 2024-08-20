package io.acme.insurancequote.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class Offer {
    private UUID id;
    private UUID productId;
    private String name;
    private LocalDateTime createdAt;
    private boolean active;
    private List<Coverage> coverages;
    private List<String> assistances;
    private MonthlyPremiumAmount monthlyPremiumAmount;

    public BigDecimal getCoverageAmountSum() {
        return coverages.stream()
                .map(Coverage::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
