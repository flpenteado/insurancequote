package io.acme.insurancequote.infrastructure.repository.entity;

import com.vladmihalcea.hibernate.type.json.JsonType;
import io.acme.insurancequote.domain.enums.QuotationCategory;
import io.acme.insurancequote.domain.models.Coverage;
import io.acme.insurancequote.domain.models.Customer;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "quotation")
@Data

public class QuotationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_id")
    private UUID productId;

    @Column(name = "offer_id")
    private UUID offerId;

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private QuotationCategory category;

    @Column(name = "total_monthly_premium_amount")
    private BigDecimal totalMonthlyPremiumAmount;

    @Column(name = "total_coverage_amount")
    private BigDecimal totalCoverageAmount;

    @Column(name = "coverages", columnDefinition = "json")
    @Type(JsonType.class)
    private List<Coverage> coverages;

    @Column(name = "assistances", columnDefinition = "json")
    @Type(JsonType.class)
    private List<String> assistances;

    @Column(name = "customer", columnDefinition = "json")
    @Type(JsonType.class)
    private Customer customer;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
