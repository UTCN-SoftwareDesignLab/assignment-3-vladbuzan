package com.assignment3.assignment3.consultation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConsultationPage {
    private int totalPages;
    private List<ConsultationDisplayDto> consultations;
}
