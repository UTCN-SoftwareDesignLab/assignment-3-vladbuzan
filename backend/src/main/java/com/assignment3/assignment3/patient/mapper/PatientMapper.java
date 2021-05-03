package com.assignment3.assignment3.patient.mapper;

import com.assignment3.assignment3.patient.dto.PatientDisplayDto;
import com.assignment3.assignment3.patient.dto.PatientRequestDto;
import com.assignment3.assignment3.patient.model.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Mapper(componentModel = "spring")
public interface PatientMapper {

    Patient patientFromRequest(PatientRequestDto request);

    @Mapping(target = "birthdate", expression = "java(stringAsFormattedDate(patient.getBirthdate()))")
    PatientDisplayDto patientDisplayFromPatient(Patient patient);

    default String stringAsFormattedDate(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.format(date);
    }
}
