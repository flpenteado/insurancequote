package io.acme.insurancequote.infrastructure.controllers.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.acme.insurancequote.domain.enums.CustomerType;
import io.acme.insurancequote.domain.enums.Gender;
import io.acme.insurancequote.domain.models.Customer;

import java.time.LocalDate;

public record CustomerResponse(

        @JsonProperty("document_number")
        String documentNumber,

        String name,

        CustomerType type,

        Gender gender,

        @JsonProperty("date_of_birth")
        LocalDate dateOfBirth,

        String email,

        @JsonProperty("phone_number")
        String phoneNumber
) {

    public static CustomerResponse fromDomain(Customer customer) {
        return new CustomerResponse(
                customer.getDocumentNumber(),
                customer.getName(),
                customer.getType(),
                customer.getGender(),
                customer.getDateOfBirth(),
                customer.getEmail(),
                customer.getPhoneNumber()
        );
    }

    public Customer toDomain() {
        return new Customer(
                documentNumber,
                name,
                type,
                gender,
                dateOfBirth,
                email,
                phoneNumber
        );
    }
}