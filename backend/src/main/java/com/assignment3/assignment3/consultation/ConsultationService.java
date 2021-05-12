package com.assignment3.assignment3.consultation;

import com.assignment3.assignment3.consultation.dto.ConsultationDisplayDto;
import com.assignment3.assignment3.consultation.dto.ConsultationPage;
import com.assignment3.assignment3.consultation.dto.ConsultationRequestDto;
import com.assignment3.assignment3.consultation.dto.ConsultationUpdateDateRequest;
import com.assignment3.assignment3.consultation.mapper.ConsultationMapper;
import com.assignment3.assignment3.consultation.model.Consultation;
import com.assignment3.assignment3.patient.PatientRepository;
import com.assignment3.assignment3.user.UserRepository;
import com.assignment3.assignment3.user.model.ERole;
import lombok.RequiredArgsConstructor;
import org.hibernate.metamodel.model.convert.internal.JpaAttributeConverterImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.assignment3.assignment3.consultation.ConsultationSpecifications.*;

@Service
@RequiredArgsConstructor
public class ConsultationService {
    private final ConsultationRepository consultationRepository;
    private final UserRepository userRepository;
    private final PatientRepository patientRepository;
    private final ConsultationMapper consultationMapper;

    public void save(ConsultationRequestDto request) {
        var doctor = userRepository.findById(request.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Couldn't find medic with given id."));
        if(!doctor.getRole().getName().equals(ERole.DOCTOR)) {
            throw new RuntimeException("User with given id is not a doctor.");
        }
        var patient = patientRepository.findById(request.getPatientId())
                .orElseThrow(() -> new RuntimeException("Couldn't find patient with given id."));

        var endDate = new Date(request.getDate().getTime() + request.getDuration() * 60000L);
        if(!isDoctorAvailable(request.getDoctorId(), request.getDate())){
            throw new RuntimeException("Doctor is not available");
        }
        var consultation = Consultation.builder()
                .doctor(doctor)
                .patient(patient)
                .startDate(request.getDate())
                .endDate(endDate)
                .description(request.getDescription())
                .build();
        consultationRepository.save(consultation);
    }

    public ConsultationPage findAll(String doctor, String patient,
                                    int page, int consultationsPerPage) {
        var pageable = PageRequest.of(page, consultationsPerPage);
        var specification = similarDoctor(doctor)
                .and(similarPatient(patient));
        var consultationsPage = consultationRepository.findAll(specification, pageable);
        var consultations = consultationsPage
                .get().map(consultationMapper::consultationDisplayFromConsultation)
                .collect(Collectors.toList());
        return ConsultationPage.builder()
                .totalPages(consultationsPage.getTotalPages())
                .consultations(consultations)
                .build();
    }

    public void delete(Long id) {
        consultationRepository.deleteById(id);
    }

    public void updateDate(ConsultationUpdateDateRequest request, Long id) {
        var consultation = findById(id);
        var newStartDate = request.getNewDate();
        var newEndDate = new Date(newStartDate.getTime() + request.getDuration() * 60000L);
        consultation.setStartDate(newStartDate);
        consultation.setEndDate(newEndDate);
        consultationRepository.save(consultation);
    }

    public void updateDoctor(Long consultationId, Long doctorId) {
        var doctor = userRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("No doctor with given id."));
        if(!doctor.getRole().getName().equals(ERole.DOCTOR)) {
            throw new RuntimeException("Given user is not a doctor");
        }
        var consultation = findById(consultationId);
        consultation.setDoctor(doctor);
        consultationRepository.save(consultation);
    }

    public void updateDescription(Long id, String description) {
        var consultation = findById(id);
        consultation.setDescription(description);
        consultationRepository.save(consultation);
    }

    public ConsultationPage findByMedic(Long id, int page, int consultationsPerPage) {
        var pageable = PageRequest.of(page, consultationsPerPage);
        var consultationsPage = consultationRepository.findAllByDoctorId(id, pageable);
        var consultations = consultationsPage.get().map(consultationMapper::consultationDisplayFromConsultation)
                .collect(Collectors.toList());
        return ConsultationPage.builder()
                .totalPages(consultationsPage.getTotalPages())
                .consultations(consultations)
                .build();
    }

    public Consultation findById(Long id) {
        return consultationRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("No consultation with given id."));
    }

    public boolean isDoctorAvailable(Long id, Date date) {

        var consultations = consultationRepository
                .findAllByStartDateIsBeforeAndEndDateIsAfter(date, date);
        return consultations.isEmpty();
    }
}
