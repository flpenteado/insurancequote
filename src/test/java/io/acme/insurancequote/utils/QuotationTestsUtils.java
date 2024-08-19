package io.acme.insurancequote.utils;

import io.acme.insurancequote.domain.models.Coverage;
import io.acme.insurancequote.domain.models.Customer;
import io.acme.insurancequote.domain.models.Quotation;
import io.acme.insurancequote.domain.enums.CustomerType;
import io.acme.insurancequote.domain.enums.Gender;
import io.acme.insurancequote.domain.enums.QuotationCategory;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


public class QuotationTestsUtils {

    public static Customer createCustomer() {
        return new Customer(
                "12345678900",
                "John Doe",
                CustomerType.NATURAL,
                Gender.MALE,
                LocalDate.of(1980, 1, 1),
                "doe@mail.com",
                "123456789"
        );
    }

    public static Coverage createCoverage() {
        return new Coverage("Coverage 1", BigDecimal.valueOf(1000));
    }

    public static Quotation createValidQuotation() {
        var customer = createCustomer();
        var coverages = List.of(createCoverage());
        var assistances = List.of("Assistance 1", "Assistance 2");
        var totalCoverageAmount = coverages.stream()
                .map(Coverage::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        var totalMonthlyPremiumAmount = BigDecimal.valueOf(100);

        return new Quotation(
                UUID.randomUUID(),
                UUID.randomUUID(),
                QuotationCategory.HOME,
                totalCoverageAmount,
                totalMonthlyPremiumAmount,
                coverages,
                assistances,
                customer
        );
    }

}
