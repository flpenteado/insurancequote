package io.acme.insurancequote.service;

import io.acme.insurancequote.domain.exception.DomainException;
import io.acme.insurancequote.domain.models.Coverage;

import io.acme.insurancequote.domain.models.MonthlyPremiumAmount;
import io.acme.insurancequote.domain.service.QuotationValidator;
import io.acme.insurancequote.utils.Utils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class QuotationValidatorTests {

    public static final QuotationValidator quotationValidator = new QuotationValidator();

    @Test
    @DisplayName("Should throw exception when product is null")
    public void shouldThrowExceptionWhenProductIsNull() {

        var quotation = Utils.createQuotation();
        var offer = Utils.createOffer();

        var exception = assertThrows(DomainException.class, () -> quotationValidator.validate(quotation, null, offer));

        assert exception.getMessage().equals("Product not found");
    }

    @Test
    @DisplayName("Should throw exception when product is not active")
    public void shouldThrowExceptionWhenProductIsNotActive() {

        var offer = Utils.createOffer();
        var product = Utils.createProduct();
        product.setActive(false);

        var quotation = Utils.createValidQuotation(product, offer);

        var exception = assertThrows(DomainException.class, () -> quotationValidator.validate(quotation, product, offer));

        assert exception.getMessage().equals("Product is not active");
    }

    @Test
    @DisplayName("Should throw exception when offer is null")
    public void shouldThrowExceptionWhenOfferIsNull() {

        var quotation = Utils.createQuotation();
        var product = Utils.createProduct();

        var exception = assertThrows(DomainException.class, () -> quotationValidator.validate(quotation, product, null));

        assert exception.getMessage().equals("Offer not found");
    }

    @Test
    @DisplayName("Should not throw exception when offer is not active")
    public void shouldNotThrowExceptionWhenOfferIsNotActive() {

        var quotation = Utils.createQuotation();
        var product = Utils.createProduct();
        var offer = Utils.createOffer();
        offer.setActive(false);

        var exception = assertThrows(DomainException.class, () -> quotationValidator.validate(quotation, product, offer));

        assert exception.getMessage().equals("Offer is not active");
    }

    @Test
    @DisplayName("Should throw exception coverages is not present in offer")
    public void shouldThrowExceptionWhenCoveragesIsNotPresentInOffer() {

        var quotation = Utils.createQuotation();

        var validCoverages = List.of(
                new Coverage("Coverage 1", BigDecimal.valueOf(1000)),
                new Coverage("Coverage 2", BigDecimal.valueOf(2000))
        );

        var invalidCoverages = List.of(
                new Coverage("Coverage 3", BigDecimal.valueOf(3000))
        );

        quotation.setCoverages(invalidCoverages);

        var product = Utils.createProduct();

        var offer = Utils.createOffer();
        offer.setCoverages(validCoverages);


        var exception = assertThrows(DomainException.class, () -> quotationValidator.validate(quotation, product, offer));

        assert exception.getMessage().equals("Coverage not found in offer");
    }

    @Test
    @DisplayName("Should throw exception when coverage amount is greater than offer")
    public void shouldThrowExceptionWhenCoverageAmountIsGreaterThanOffer() {

        var quotation = Utils.createQuotation();

        var validCoverages = List.of(
                new Coverage("Coverage 1", BigDecimal.valueOf(1000)),
                new Coverage("Coverage 2", BigDecimal.valueOf(2000))
        );

        var invalidCoverages = List.of(
                new Coverage("Coverage 1", BigDecimal.valueOf(3000))
        );

        quotation.setCoverages(invalidCoverages);

        var product = Utils.createProduct();

        var offer = Utils.createOffer();
        offer.setCoverages(validCoverages);

        var exception = assertThrows(DomainException.class, () -> quotationValidator.validate(quotation, product, offer));

        assert exception.getMessage().equals("Coverage amount is greater than the offer");
    }

    @Test
    @DisplayName("Should throw exception when assistances is not present in offer")
    public void shouldThrowExceptionWhenAssistancesIsNotPresentInOffer() {

        var quotation = Utils.createQuotation();
        var validAssistances = List.of("Assistance 1", "Assistance 2");

        var invalidAssistances = List.of("Assistance 3");
        quotation.setAssistances(invalidAssistances);

        var offer = Utils.createOffer();
        offer.setAssistances(validAssistances);

        var product = Utils.createProduct();

        var exception = assertThrows(DomainException.class, () -> quotationValidator.validate(quotation, product, offer));

        assert exception.getMessage().equals("Assistance not found in offer");
    }

    @Test
    @DisplayName("Should throw exception when monthly premium is not within the range")
    public void shouldThrowExceptionWhenMonthlyPremiumIsNotWithinTheRange() {

        var quotation = Utils.createQuotation();
        var product = Utils.createProduct();
        var offer = Utils.createOffer();

        var monthlyPremiumAmount = new MonthlyPremiumAmount(
                BigDecimal.valueOf(100),
                BigDecimal.valueOf(200),
                BigDecimal.valueOf(150)
        );

        offer.setMonthlyPremiumAmount(monthlyPremiumAmount);
        quotation.setTotalMonthlyPremiumAmount(BigDecimal.valueOf(300));

        var exception = assertThrows(DomainException.class, () -> quotationValidator.validate(quotation, product, offer));

        assert exception.getMessage().equals("Monthly premium is not within the range");
    }


    @Test
    @DisplayName("Should throw exception when total coverage amount is not equal to the sum of the coverages")
    public void shouldThrowExceptionWhenTotalCoverageAmountIsNotEqualToTheSumOfTheCoverages() {

        var product = Utils.createProduct();
        var offer = Utils.createOffer();

        var quotation = Utils.createValidQuotation(product, offer);


        var invalidTotalCoverageAmount = quotation.getTotalCoverageAmount().add(BigDecimal.valueOf(100));
        quotation.setTotalCoverageAmount(invalidTotalCoverageAmount);

        var exception = assertThrows(DomainException.class, () -> quotationValidator.validate(quotation, product, offer));

        assert exception.getMessage().equals("Total coverage amount is not equal to the sum of the coverages");
    }

    @Test
    @DisplayName("Should not throw exception when all validations are successful")
    public void shouldNotThrowExceptionWhenAllValidationsAreSuccessful() {

        var product = Utils.createProduct();
        var offer = Utils.createOffer();
        var quotation = Utils.createValidQuotation(product, offer);

        assertDoesNotThrow(() -> quotationValidator.validate(quotation, product, offer));
    }
}
