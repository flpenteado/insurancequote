package io.acme.insurancequote.infrastructure.controllers;

import io.acme.insurancequote.application.usecase.CreateQuotationUseCase;
import io.acme.insurancequote.application.usecase.GetQuotationUseCase;
import io.acme.insurancequote.domain.models.Quotation;
import io.acme.insurancequote.infrastructure.controllers.dto.CreateQuotationRequest;
import io.acme.insurancequote.infrastructure.controllers.dto.QuotationResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/quotation")
public class QuotationController {
    private final CreateQuotationUseCase createQuotationUseCase;
    private final GetQuotationUseCase getQuotationUseCase;

    public QuotationController(
            CreateQuotationUseCase createQuotationUseCase,
            GetQuotationUseCase getQuotationUseCase
    ) {
        this.createQuotationUseCase = createQuotationUseCase;
        this.getQuotationUseCase = getQuotationUseCase;
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuotationResponse> getQuotation(@PathVariable long id) {
        return getQuotationUseCase.getQuotationById(id)
                .map(quotation -> ResponseEntity.ok(QuotationResponse.fromDomain(quotation)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<QuotationResponse> createQuotation(@RequestBody CreateQuotationRequest request) {
        Quotation quotation = request.toDomain();
        Quotation createdQuotation = createQuotationUseCase.execute(quotation);

        return ResponseEntity.ok(QuotationResponse.fromDomain(createdQuotation));
    }
}
