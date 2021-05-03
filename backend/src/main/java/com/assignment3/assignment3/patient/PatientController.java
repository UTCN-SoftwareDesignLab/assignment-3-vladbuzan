package com.assignment3.assignment3.patient;

import com.assignment3.assignment3.patient.dto.PatientRequestDto;
import com.assignment3.assignment3.security.dto.MessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.assignment3.assignment3.UrlMapping.*;

@RestController
@RequestMapping(PATIENT)
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @PostMapping
    public ResponseEntity<?> savePatient(@RequestBody PatientRequestDto patient) {
        try {
            patientService.save(patient);
            return ResponseEntity.ok(new MessageResponse(
                    "Patient saved successfully."
            ));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(
                    new MessageResponse(ex.getMessage())
            );
        }
    }

    @GetMapping
    public ResponseEntity<?> findAll(@RequestParam String name, @RequestParam int page,
                                     @RequestParam int patientsPerPage) {
        try {
            var patients = patientService.findAll(name, page, patientsPerPage);
            return ResponseEntity.ok(patients);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(
                    new MessageResponse(ex.getMessage())
            );
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updatePatient(@PathVariable Long id, @RequestBody PatientRequestDto request) {
        try  {
            patientService.updatePatient(id, request);
            return ResponseEntity.ok(new MessageResponse(
                    "Patient updated successfully."
            ));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(
                    new MessageResponse(ex.getMessage())
            );
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deletePatient(@PathVariable Long id) {
        try {
            patientService.delete(id);
            return ResponseEntity.ok(new MessageResponse(
                    "Patient deleted successfully."
            ));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(
                    new MessageResponse(ex.getMessage())
            );
        }
    }


}
