package com.assignment3.assignment3.consultation.dto;

import com.assignment3.assignment3.user.dto.UserDisplayDto;
import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConsultationDisplayDto {
    ConsultationSimpleDto details;
    UserDisplayDto doctor;
    UserDisplayDto patient;
}
