package io.acme.insurancequote.domain.service;

import io.acme.insurancequote.domain.exception.DomainException;
import io.acme.insurancequote.domain.models.Offer;
import io.acme.insurancequote.domain.models.Product;
import io.acme.insurancequote.domain.models.Quotation;

public class QuotationValidator {

    public void validate(Quotation quotation, Product product, Offer offer) {
        this.validateProduct(product);
        this.validateOffer(offer);
        this.validateCoverage(quotation, offer);
        this.validateAssistance(quotation, offer);
        this.validateMonthlyPremium(quotation, offer);
        this.validateTotalCoverageAmount(quotation);
    }

    private void validateProduct(Product product) {
        if (product == null) {
            throw new DomainException("Product not found");
        }

        if (!product.isActive()) {
            throw new DomainException("Product is not active");
        }
    }

    private void validateOffer(Offer offer) {
        if (offer == null) {
            throw new DomainException("Offer not found");
        }

        if (!offer.isActive()) {
            throw new DomainException("Offer is not active");
        }
    }

    private void validateCoverage(Quotation quotation, Offer offer) {
        for (var coverage : quotation.getCoverages()) {
            var offerCoverage = offer.getCoverages().stream()
                    .filter(c -> c.getName().equals(coverage.getName()))
                    .findFirst()
                    .orElseThrow(() -> new DomainException("Coverage not found in offer"));

            if (coverage.getAmount().compareTo(offerCoverage.getAmount()) > 0) {
                throw new DomainException("Coverage amount is greater than the offer");
            }
        }
    }

    private void validateAssistance(Quotation quotation, Offer offer) {
        for (var assistance : quotation.getAssistances()) {
            if (!offer.getAssistances().contains(assistance)) {
                throw new DomainException("Assistance not found in offer");
            }
        }
    }

    private void validateMonthlyPremium(Quotation quotation, Offer offer) {
        var minMonthlyPremium = offer.getMonthlyPremiumAmount().getMinAmount();
        var maxMonthlyPremium = offer.getMonthlyPremiumAmount().getMaxAmount();

        if (quotation.getTotalMonthlyPremiumAmount().compareTo(minMonthlyPremium) < 0 ||
            quotation.getTotalMonthlyPremiumAmount().compareTo(maxMonthlyPremium) > 0) {
            throw new DomainException("Monthly premium is not within the range");
        }
    }

    private void validateTotalCoverageAmount(Quotation quotation) {
        var totalCoverageAmount = quotation.getTotalCoverageAmount();
        var coverageAmountSum = quotation.getCoverageAmountSum();

        if(coverageAmountSum.compareTo(totalCoverageAmount) != 0) {
                throw new DomainException("Total coverage amount is not equal to the sum of the coverages");
        }
    }
}
