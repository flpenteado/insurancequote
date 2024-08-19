package io.acme.insurancequote.domain;

import io.acme.insurancequote.domain.models.Coverage;
import io.acme.insurancequote.utils.Utils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class OfferTests {
    @Test
    @DisplayName("Should create a valid offer")
    public void shouldCreateAValidOffer() {
        var offer = Utils.createOffer();

        assertNotNull (offer);
    }

    @Test
    @DisplayName("Should calculate offer total amount sum correctly")
    public void shouldCalculateOfferTotalAmountSumCorrectly() {
        var offer = Utils.createOffer();
        var coverages = List.of(
                new Coverage("Coverage 1",  BigDecimal.valueOf(1000)),
                new Coverage("Coverage 2",  BigDecimal.valueOf(2000))
        );
        offer.setCoverages(coverages);

        var expectedTotalCoverageAmount = BigDecimal.valueOf(3000);
        var totalCoverageAmount = offer.getCoverageAmountSum();

        assertNotNull(totalCoverageAmount);
        assert(totalCoverageAmount.compareTo(expectedTotalCoverageAmount) == 0);
    }
}
