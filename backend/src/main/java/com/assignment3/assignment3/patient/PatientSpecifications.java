package com.assignment3.assignment3.patient;

import com.assignment3.assignment3.patient.model.Patient;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class PatientSpecifications {
    public static Specification<Patient> similarName(String name) {
        return new Specification<Patient>() {
            @Override
            public Predicate toPredicate(Root<Patient> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.or(
                        criteriaBuilder.like(root.get("firstname"), "%" + name + "%"),
                        criteriaBuilder.like(root.get("lastname"), "%" + name + "%")
                );
            }
        };
    }
}
