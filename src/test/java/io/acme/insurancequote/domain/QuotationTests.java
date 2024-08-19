package io.acme.insurancequote.domain;

import io.acme.insurancequote.domain.models.Coverage;
import io.acme.insurancequote.domain.models.Quotation;
import io.acme.insurancequote.utils.QuotationTestsUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;


public class QuotationTests {

    @Test
    @DisplayName("Should create a valid quotation")
    public void shouldCreateAValidQuotation () {
        Quotation quotation = QuotationTestsUtils.createValidQuotation();

        assertNotNull(quotation);
    }

    @Test
    @DisplayName("Should calculate coverage total amount sum correctly")
    public void shouldCalculateCoverageTotalAmountSumCorrectly() {
        Quotation quotation = QuotationTestsUtils.createValidQuotation();
        var coverages = List.of(
                new Coverage("Coverage 1",  BigDecimal.valueOf(1000)),
                new Coverage("Coverage 2",  BigDecimal.valueOf(2000))
        );
        quotation.setCoverages(coverages);

        var expectedTotalCoverageAmount = BigDecimal.valueOf(3000);
        var totalCoverageAmount = quotation.getCoverageAmountSum();

        assertNotNull(totalCoverageAmount);
        assert(totalCoverageAmount.compareTo(expectedTotalCoverageAmount) == 0);
    }
}
