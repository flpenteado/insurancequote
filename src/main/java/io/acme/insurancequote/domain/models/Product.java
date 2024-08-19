package io.acme.insurancequote.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class Product {
    private UUID id;
    private String name;
    private LocalDateTime createdAt;
    private boolean active;
    private List<Offer> offers;
}
