package com.assignment3.assignment3.consultation.model;

import com.assignment3.assignment3.patient.model.Patient;
import com.assignment3.assignment3.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Consultation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient")
    @NotNull
    private Patient patient;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "doctor")
    @NotNull
    private User doctor;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date startDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date endDate;

    @Column
    private String description;
}
