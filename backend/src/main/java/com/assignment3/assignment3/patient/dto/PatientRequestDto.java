package com.assignment3.assignment3.patient.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.SuperBuilder;
import net.bytebuddy.implementation.bind.annotation.Super;

import java.util.Date;

@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatientRequestDto extends PatientBaseDto{
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date birthdate;
    private String address;
}
