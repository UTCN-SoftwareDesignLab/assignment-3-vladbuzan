package com.assignment3.assignment3.consultation;

import com.assignment3.assignment3.consultation.dto.ConsultationRequestDto;
import com.assignment3.assignment3.consultation.dto.ConsultationUpdateDateRequest;
import com.assignment3.assignment3.security.dto.MessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import static com.assignment3.assignment3.UrlMapping.*;

@RestController
@RequestMapping(CONSULTATION)
@RequiredArgsConstructor
public class ConsultationController {
    private final ConsultationService consultationService;

    @PostMapping
    public ResponseEntity<?> saveConsultation(@RequestBody ConsultationRequestDto consultation){
        try {
            consultationService.save(consultation);
            return ResponseEntity.ok(new MessageResponse(
                    "Consultation saved successfully."
            ));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(
                    new MessageResponse(ex.getMessage())
            );
        }
    }

    @GetMapping
    public ResponseEntity<?> getAll(@RequestParam String doctor, @RequestParam String patient,
                                    @RequestParam Integer page, @RequestParam Integer consultationPerPage) {
        try {
            var consultations = consultationService.findAll(doctor,
                    patient, page, consultationPerPage);
            return ResponseEntity.ok(consultations);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(
                    new MessageResponse(ex.getMessage())
            );
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try {
            consultationService.delete(id);
            return ResponseEntity.ok(
                    new MessageResponse(
                            "Consultation deleted successfully"
                    )
            );
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(
                    new MessageResponse(ex.getMessage())
            );
        }
    }

    @PatchMapping("{id}")
    public ResponseEntity<?> updateDate(@RequestBody ConsultationUpdateDateRequest request,
                                        @PathVariable Long id) {
        try {
            consultationService.updateDate(request, id);
            return ResponseEntity.ok(
                    new MessageResponse(
                            "Date updated successfully."
                    )
            );
        } catch(Exception ex) {
            return ResponseEntity.badRequest().body(
                    new MessageResponse(ex.getMessage())
            );
        }
    }

    @PatchMapping("{consultationId}/{doctorId}")
    public ResponseEntity<?> updateDoctor(@PathVariable Long consultationId,
                                          @PathVariable Long doctorId) {
        try {
            consultationService.updateDoctor(consultationId, doctorId);
            return ResponseEntity.ok(
                    new MessageResponse(
                            "Doctor updated successfully."
                    )
            );
        } catch(Exception ex) {
            return ResponseEntity.badRequest().body(
                    new MessageResponse(ex.getMessage())
            );
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateDescription(@RequestBody String description, @PathVariable Long id) {
        try {
            consultationService.updateDescription(id, description);
            return ResponseEntity.ok(
                    new MessageResponse(
                            "Description updated successfully."
                    )
            );
        } catch(Exception ex) {
            return ResponseEntity.badRequest().body(
                    new MessageResponse(ex.getMessage())
            );
        }
    }

}
