package com.newbeginnings.registry.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor @EqualsAndHashCode
public class Participant {

    @Getter @Setter private String referenceNumber;
    @Getter @Setter private String firstName;
    @Getter @Setter private String lastName;
    @Getter @Setter private LocalDate dateOfBirth;
    @Getter @Setter private long phoneNumber;
    @Getter @Setter private String address;
}
