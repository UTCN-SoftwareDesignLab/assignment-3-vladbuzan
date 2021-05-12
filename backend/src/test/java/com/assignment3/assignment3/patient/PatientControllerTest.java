package com.assignment3.assignment3.patient;

import com.assignment3.assignment3.BaseControllerTest;
import com.assignment3.assignment3.TestCreationFactory;
import com.assignment3.assignment3.patient.dto.PatientDisplayDto;
import com.assignment3.assignment3.patient.dto.PatientPage;
import com.assignment3.assignment3.patient.dto.PatientRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import java.util.List;
import static org.mockito.Mockito.when;
import static com.assignment3.assignment3.UrlMapping.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.doNothing;

public class PatientControllerTest extends BaseControllerTest {
    @InjectMocks
    private PatientController patientController;

    @Mock
    private PatientService patientService;

    @BeforeEach
    protected void setup() {
        super.setUp();
        MockitoAnnotations.openMocks(this);
        patientController = new PatientController(patientService);
        mockMvc = MockMvcBuilders.standaloneSetup(patientController).build();
    }

    @Test
    void allPatients() throws Exception {
        List<PatientDisplayDto> patients = TestCreationFactory.listOf(PatientDisplayDto.class);
        PatientPage page = PatientPage.builder()
                .patients(patients)
                .totalPages(3)
                .build();
        when(patientService.findAll("",0, 100)).thenReturn(page);
        ResultActions result = mockMvc.perform(get(PATIENT + "?name=&page=0&patientsPerPage=0"));
        result.andExpect(status().isOk());
    }

    @Test
    void savePatient() throws Exception {
        var request = TestCreationFactory.newPatientRequestDto();
        doNothing().when(patientService).save(request);
        ResultActions resultActions = performPostWithRequestBody(PATIENT, request);
        resultActions.andExpect(status().isOk());
    }

    @Test
    void updatePatient() throws Exception {
        var request = TestCreationFactory.newPatientRequestDto();
        doNothing().when(patientService).updatePatient(1L, request);
        ResultActions resultActions = performPutWithRequestBodyAndPathVariable(PATIENT + "/{id}", request,1);
        resultActions.andExpect(status().isOk());
    }

    @Test
    void deletePatient() throws Exception {
        doNothing().when(patientService).delete(1L);
        ResultActions resultActions = performDeleteWIthPathVariable(PATIENT + "/{id}", 1L);
        resultActions.andExpect(status().isOk());
    }
}
