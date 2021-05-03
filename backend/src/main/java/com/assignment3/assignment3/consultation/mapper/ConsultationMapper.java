package com.assignment3.assignment3.consultation.mapper;

import com.assignment3.assignment3.consultation.dto.ConsultationDisplayDto;
import com.assignment3.assignment3.consultation.dto.ConsultationSimpleDto;
import com.assignment3.assignment3.consultation.model.Consultation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Mapper(componentModel = "spring")
public interface ConsultationMapper {

    @Mappings ({
      @Mapping(target = "startDate", expression = "java(stringAsFormattedDate(consultation.getStartDate()))"),
      @Mapping(target = "endDate", expression = "java(stringAsFormattedDate(consultation.getEndDate()))")
    })
    ConsultationSimpleDto consultationSimpleFromConsultation(Consultation consultation);

    @Mapping(target = "details", expression = "java(consultationSimpleFromConsultation(consultation))")
    ConsultationDisplayDto consultationDisplayFromConsultation(Consultation consultation);

    default String stringAsFormattedDate(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        return dateFormat.format(date);
    }
}
