package com.assignment3.assignment3.consultation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConsultationSimpleDto {
    private String startDate;
    private String endDate;
    private String description;
}
