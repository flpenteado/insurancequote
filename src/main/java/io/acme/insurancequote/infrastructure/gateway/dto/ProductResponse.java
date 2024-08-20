package io.acme.insurancequote.infrastructure.gateway.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.acme.insurancequote.domain.models.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
public class ProductResponse {

    private UUID id;

    private String name;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    private boolean active;

    private UUID[] offers;

    public Product toProduct() {
        return new Product(
                id,
                name,
                createdAt,
                active,
                null
        );
    }
}
