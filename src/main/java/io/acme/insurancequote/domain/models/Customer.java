package io.acme.insurancequote.domain.models;

import io.acme.insurancequote.domain.enums.CustomerType;
import io.acme.insurancequote.domain.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Customer {
    private String documentNumber;
    private String name;
    private CustomerType type;
    private Gender gender;
    private LocalDate dateOfBirth;
    private String email;
    private String phoneNumber;
}
