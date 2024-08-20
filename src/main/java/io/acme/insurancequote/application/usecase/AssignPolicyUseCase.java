package io.acme.insurancequote.application.usecase;

import io.acme.insurancequote.application.messaging.dto.PolicyIssued;
import io.acme.insurancequote.application.repository.QuotationRepository;
import io.acme.insurancequote.domain.exception.DomainException;

public class AssignPolicyUseCase {
    private final QuotationRepository quotationRepository;

    public AssignPolicyUseCase(QuotationRepository quotationRepository) {
        this.quotationRepository = quotationRepository;
    }

    public void execute(PolicyIssued policyIssued) {
        var quotation = quotationRepository.findById(policyIssued.quotationId());

        if (quotation.isEmpty()) {
            throw new DomainException("Quotation not found");
        }

        var quotationEntity = quotation.get();

        quotationEntity.setInsurancePolicyId(policyIssued.policyId());

        quotationRepository.save(quotationEntity);
    }
}
