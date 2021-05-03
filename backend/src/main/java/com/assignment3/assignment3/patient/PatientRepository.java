package com.assignment3.assignment3.patient;

import com.assignment3.assignment3.patient.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PatientRepository extends JpaRepository<Patient, Long>,
        JpaSpecificationExecutor<Patient> {
}
