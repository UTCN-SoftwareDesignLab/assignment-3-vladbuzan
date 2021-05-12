package com.assignment3.assignment3.patient;

import com.assignment3.assignment3.TestCreationFactory;
import com.assignment3.assignment3.patient.dto.PatientDisplayDto;
import com.assignment3.assignment3.patient.dto.PatientPage;
import com.assignment3.assignment3.patient.mapper.PatientMapper;
import com.assignment3.assignment3.patient.model.Patient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;

public class PatientServiceTest {

    @InjectMocks
    private PatientService patientService;

    @Mock
    PatientRepository patientRepository;

    @Mock
    private PatientMapper patientMapper;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        patientService = new PatientService(patientRepository, patientMapper);
    }

    @Test
    void save() {
        var request = TestCreationFactory.newPatientRequestDto();
        var patient = Patient.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .address(request.getAddress())
                .cnp(request.getCnp())
                .birthdate(request.getBirthdate())
                .build();
        when(patientMapper.patientFromRequest(request)).thenReturn(patient);
        when(patientRepository.save(patient)).thenReturn(patient);
        Assertions.assertDoesNotThrow(() -> patientService.save(request));
    }

    @Test
    void findAll() {
        List<PatientDisplayDto> patients = TestCreationFactory.listOf(PatientDisplayDto.class);
        List<Patient> patients1 = TestCreationFactory.listOf(Patient.class);
        var page = PatientPage.builder()
                .patients(patients)
                .totalPages(3)
                .build();
        var specification = PatientSpecifications.similarName("");
        var pageable = PageRequest.of(3, 3);
        //how do i mock a method that returns a page?
    }

    @Test
    void updatePatient() {
        var request = TestCreationFactory.newPatientRequestDto();
        var patient = TestCreationFactory.newPatient();
        when(patientRepository.findById(3L)).thenReturn(Optional.of(patient));
        patient.setFirstname(request.getFirstname());
        patient.setLastname(request.getLastname());
        patient.setAddress(request.getAddress());
        patient.setBirthdate(request.getBirthdate());
        patient.setCnp(request.getCnp());
        when(patientRepository.save(patient)).thenReturn(patient);
        Assertions.assertDoesNotThrow(() -> patientService.updatePatient(3L, request));
    }

    @Test
    void delete() {
        doNothing().when(patientRepository).deleteById(3L);
        Assertions.assertDoesNotThrow(() -> patientService.delete(3L));
    }
}
