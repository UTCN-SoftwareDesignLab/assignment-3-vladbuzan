package com.assignment3.assignment3.patient.model;

import com.assignment3.assignment3.consultation.model.Consultation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String firstname;

    @Column(nullable = false, length = 120)
    private String lastname;

    @Column(nullable = false, length = 13)
    private String cnp;

    @Temporal(TemporalType.DATE)
    @Column
    private Date birthdate;

    @Column
    private String address;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.REMOVE)
    private List<Consultation> consultations;

}
