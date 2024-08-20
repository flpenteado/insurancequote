package io.acme.insurancequote.application.messaging.dto;

public record PolicyIssued (
        long quotationId,
        long policyId
) {

}