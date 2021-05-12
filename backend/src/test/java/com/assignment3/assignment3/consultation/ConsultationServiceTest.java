package com.assignment3.assignment3.consultation;


import com.assignment3.assignment3.TestCreationFactory;
import com.assignment3.assignment3.consultation.dto.ConsultationUpdateDateRequest;
import com.assignment3.assignment3.consultation.mapper.ConsultationMapper;
import com.assignment3.assignment3.consultation.model.Consultation;
import com.assignment3.assignment3.patient.PatientRepository;
import com.assignment3.assignment3.user.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;

public class ConsultationServiceTest {
    @InjectMocks
    private ConsultationService consultationService;

    @Mock
    private ConsultationRepository consultationRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private ConsultationMapper consultationMapper;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        consultationService = new ConsultationService(consultationRepository, userRepository, patientRepository, consultationMapper);
    }

    @Test
    void save() {
        var request = TestCreationFactory.newConsultationRequestDto();
        var doctor = TestCreationFactory.newUser();
        var patient = TestCreationFactory.newPatient();
        var endDate = new Date(request.getDate().getTime() + request.getDuration() * 60000L);
        var consultation = Consultation.builder()
                .doctor(doctor)
                .patient(patient)
                .startDate(request.getDate())
                .endDate(endDate)
                .description(request.getDescription())
                .build();
        when(userRepository.findById(request.getDoctorId())).thenReturn(Optional.of(doctor));
        when(patientRepository.findById(request.getPatientId())).thenReturn(Optional.of(patient));
        when(consultationRepository.findAllByStartDateIsBeforeAndEndDateIsAfter(request.getDate(), request.getDate())).thenReturn(new ArrayList<>());
        when(consultationRepository.save(consultation)).thenReturn(consultation);
        Assertions.assertDoesNotThrow(() -> consultationService.save(request));
    }

    @Test
    void findAll() {

    }

    @Test
    void delete() {
        doNothing().when(consultationRepository).deleteById(3L);
        Assertions.assertDoesNotThrow(()-> consultationService.delete(3L));
    }

    @Test
    void updateDate() {
        var request = ConsultationUpdateDateRequest.builder()
                .newDate(new Date())
                .duration(50)
                .build();
        var consultation = TestCreationFactory.newConsultation();
        when(consultationRepository.findById(consultation.getId())).thenReturn(Optional.of(consultation));
        when(consultationRepository.save(consultation)).thenReturn(consultation);
        Assertions.assertDoesNotThrow(()->consultationService.updateDate(request, consultation.getId()));
    }

    @Test
    void updateDoctor() {
        var doctor = TestCreationFactory.newUser();
        var consultation = TestCreationFactory.newConsultation();
        when(userRepository.findById(doctor.getId())).thenReturn(Optional.of(doctor));
        when(consultationRepository.findById(consultation.getId())).thenReturn(Optional.of(consultation));
        when(consultationRepository.save(consultation)).thenReturn(consultation);
        Assertions.assertDoesNotThrow(()->consultationService.updateDoctor(consultation.getId(), doctor.getId()));
    }

    @Test
    void updateDescription(){
        String description = TestCreationFactory.randomString();
        var consultation = TestCreationFactory.newConsultation();
        when(consultationRepository.findById(consultation.getId())).thenReturn(Optional.of(consultation));
        when(consultationRepository.save(consultation)).thenReturn(consultation);
        Assertions.assertDoesNotThrow(()->consultationService.updateDescription(consultation.getId(),description));
    }

    @Test
    void findByMedic() {
        //TODO 
    }

}
