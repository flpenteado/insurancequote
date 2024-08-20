package io.acme.insurancequote.application.messaging;

import io.acme.insurancequote.application.messaging.dto.PolicyIssued;
import io.acme.insurancequote.domain.models.Quotation;

public interface QuotationMessage {
    void send(Quotation message);
    void receive(PolicyIssued message);

}
