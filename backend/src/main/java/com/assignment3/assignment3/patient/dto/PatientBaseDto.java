package com.assignment3.assignment3.patient.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor

public class PatientBaseDto {
    private String firstname;
    private String lastname;
    private String cnp;
}
