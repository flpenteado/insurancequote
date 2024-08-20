package io.acme.insurancequote.infrastructure.repository;

import io.acme.insurancequote.InsurancequoteApplicationTests;
import io.acme.insurancequote.application.repository.QuotationRepository;
import io.acme.insurancequote.domain.models.Customer;
import io.acme.insurancequote.utils.Utils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class QuotationRepositoryTests extends InsurancequoteApplicationTests {

    @Autowired
    QuotationRepository quotationRepository;

    @Test
    @DisplayName("Should save a quotation")
    public void shouldSaveQuotation() {
        var product = Utils.createProduct();
        var offer = Utils.createOffer();
        var quotation = Utils.createValidQuotation(product, offer);

        var savedQuotation = quotationRepository.save(quotation);

        assertNotNull(savedQuotation);
        assert savedQuotation.getId() > 0;
        assert savedQuotation.getProductId().equals(product.getId());
        assert savedQuotation.getOfferId().equals(offer.getId());
        assert savedQuotation.getCoverages().equals(quotation.getCoverages());
        assert savedQuotation.getAssistances().equals(quotation.getAssistances());
        assert savedQuotation.getTotalCoverageAmount().equals(quotation.getTotalCoverageAmount());
        assert savedQuotation.getTotalMonthlyPremiumAmount().equals(quotation.getTotalMonthlyPremiumAmount());
    }

    @Test
    @DisplayName("Should find a quotation by id")
    public void shouldFindQuotationById() {
        var product = Utils.createProduct();
        var offer = Utils.createOffer();
        var quotation = Utils.createValidQuotation(product, offer);

        var savedQuotation = quotationRepository.save(quotation);

        var foundQuotation = quotationRepository.findById(savedQuotation.getId());

        assertNotNull(foundQuotation);
        assert foundQuotation.isPresent();
        assert foundQuotation.get().getId() == savedQuotation.getId();
        assert foundQuotation.get().getProductId().equals(product.getId());
        assert foundQuotation.get().getOfferId().equals(offer.getId());
        assert foundQuotation.get().getCoverages().equals(quotation.getCoverages());
        assert foundQuotation.get().getAssistances().equals(quotation.getAssistances());
        assert foundQuotation.get().getTotalCoverageAmount().compareTo(quotation.getTotalCoverageAmount()) == 0;
        assert foundQuotation.get().getTotalMonthlyPremiumAmount().compareTo(quotation.getTotalMonthlyPremiumAmount()) == 0;
    }

    @Test
    @DisplayName("Should update a quotation")
    public void shouldUpdateQuotation() {
        var product = Utils.createProduct();
        var offer = Utils.createOffer();
        var quotation = Utils.createValidQuotation(product, offer);

        var savedQuotation = quotationRepository.save(quotation);

        var newCustomer = Utils.createCustomer();

        savedQuotation.setCustomer(newCustomer);

        var updatedQuotation = quotationRepository.save(savedQuotation);

        assertNotNull(updatedQuotation);
        assert updatedQuotation.getId() == savedQuotation.getId();
        assert updatedQuotation.getProductId().equals(product.getId());
        assert updatedQuotation.getOfferId().equals(offer.getId());
        assert updatedQuotation.getCoverages().equals(savedQuotation.getCoverages());
        assert updatedQuotation.getAssistances().equals(savedQuotation.getAssistances());
        assert updatedQuotation.getTotalCoverageAmount().compareTo(savedQuotation.getTotalCoverageAmount()) == 0;
        assert updatedQuotation.getTotalMonthlyPremiumAmount().compareTo(savedQuotation.getTotalMonthlyPremiumAmount()) == 0;
        assert updatedQuotation.getCustomer().equals(newCustomer);
    }
}
