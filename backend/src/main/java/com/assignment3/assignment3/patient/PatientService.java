package com.assignment3.assignment3.patient;

import com.assignment3.assignment3.patient.dto.PatientDisplayDto;
import com.assignment3.assignment3.patient.dto.PatientPage;
import com.assignment3.assignment3.patient.dto.PatientRequestDto;
import com.assignment3.assignment3.patient.mapper.PatientMapper;
import com.assignment3.assignment3.patient.model.Patient;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    public void save(PatientRequestDto patient) {
        patientRepository.save(patientMapper.patientFromRequest(patient));
    }

    public PatientPage findAll(String name, int page, int patientsPerPage) {
        var pageable = PageRequest.of(page, patientsPerPage);
        var patientPage = patientRepository
                .findAll(PatientSpecifications.similarName(name), pageable);
        var patients = patientPage.get()
                .map(patientMapper::patientDisplayFromPatient).collect(Collectors.toList());
        return PatientPage.builder()
                .totalPages(patientPage.getTotalPages())
                .patients(patients)
                .build();
    }

    public void updatePatient(Long id, PatientRequestDto request) {
        var patient = findById(id);
        patient.setFirstname(request.getFirstname());
        patient.setLastname(request.getLastname());
        patient.setAddress(request.getAddress());
        patient.setBirthdate(request.getBirthdate());
        patient.setCnp(request.getCnp());
        patientRepository.save(patient);
    }

    private Patient findById(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Couldn't find patient with given id"));
    }

    public void delete(Long id) {
        patientRepository.deleteById(id);
    }

    public long count() {
        return patientRepository.count();
    }
}
