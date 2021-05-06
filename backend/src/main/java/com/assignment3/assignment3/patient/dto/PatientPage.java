package com.assignment3.assignment3.patient.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatientPage {
    private int totalPages;
    private List<PatientDisplayDto> patients;
}
