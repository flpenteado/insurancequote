package io.acme.insurancequote.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class MonthlyPremiumAmount {
    private BigDecimal minAmount;
    private BigDecimal maxAmount;
    private BigDecimal suggestedAmount;
}
