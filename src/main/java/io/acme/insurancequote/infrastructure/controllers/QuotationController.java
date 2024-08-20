package io.acme.insurancequote.infrastructure.controllers;

import io.acme.insurancequote.application.usecase.CreateQuotationUseCase;
import io.acme.insurancequote.domain.models.Quotation;
import io.acme.insurancequote.infrastructure.controllers.dto.CreateQuotationRequest;
import io.acme.insurancequote.infrastructure.controllers.dto.QuotationResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/quotation")
public class QuotationController {
    private final CreateQuotationUseCase createQuotationUseCase;

    @Value("${spring.application.name}")
    private String appName;

    public QuotationController(CreateQuotationUseCase createQuotationUseCase) {
        this.createQuotationUseCase = createQuotationUseCase;
    }

    @PostMapping
    public ResponseEntity<QuotationResponse> createQuotation(@RequestBody CreateQuotationRequest request) {
        Quotation quotation = request.toDomain();
        Quotation createdQuotation = createQuotationUseCase.execute(quotation);

        return ResponseEntity.ok(QuotationResponse.fromDomain(createdQuotation));
    }
}
