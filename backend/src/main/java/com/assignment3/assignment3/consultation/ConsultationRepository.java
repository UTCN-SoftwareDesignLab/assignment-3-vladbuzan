package com.assignment3.assignment3.consultation;

import com.assignment3.assignment3.consultation.model.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ConsultationRepository extends JpaRepository <Consultation, Long>,
        JpaSpecificationExecutor<Consultation> {


}
