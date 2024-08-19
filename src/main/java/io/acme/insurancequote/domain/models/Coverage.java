package io.acme.insurancequote.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class Coverage {
    private String name;
    private BigDecimal amount;
}
