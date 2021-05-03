package com.assignment3.assignment3.patient.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatientDisplayDto extends PatientBaseDto{
    private Long id;
    private String birthdate;
    private String address;
}
