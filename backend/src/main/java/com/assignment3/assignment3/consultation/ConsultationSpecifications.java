package com.assignment3.assignment3.consultation;

import com.assignment3.assignment3.consultation.model.Consultation;
import com.assignment3.assignment3.patient.model.Patient;
import com.assignment3.assignment3.user.model.User;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class ConsultationSpecifications {
    public static Specification<Consultation> similarDoctor(String name) {
        return new Specification<Consultation>() {
            @Override
            public Predicate toPredicate(Root<Consultation> root, CriteriaQuery<?> query,
                                         CriteriaBuilder criteriaBuilder) {
                Subquery<User> userSubQuery = query.subquery(User.class);
                Root<User> userRoot = userSubQuery.from(User.class);
                Subquery<User> userCriteria = userSubQuery.select(userRoot).where(
                        criteriaBuilder.and(
                            criteriaBuilder.or(
                                criteriaBuilder.like(userRoot.get("firstname"), "%" + name + "%"),
                                criteriaBuilder.like(userRoot.get("lastname"), "%" + name + "%")
                            ),
                                criteriaBuilder.equal(root.get("doctor").get("id"), userRoot.get("id")))
                );
                return criteriaBuilder.exists(userCriteria);
            }
        };
    }

    public static Specification<Consultation> similarPatient(String name) {
        return new Specification<Consultation>() {
            @Override
            public Predicate toPredicate(Root<Consultation> root, CriteriaQuery<?> query,
                                         CriteriaBuilder criteriaBuilder) {
                Subquery<Patient> patientSubQuery = query.subquery(Patient.class);
                Root<Patient> patientRoot = patientSubQuery.from(Patient.class);
                Subquery<Patient> patientCriteria = patientSubQuery.select(patientRoot).where(
                        criteriaBuilder.and(
                                criteriaBuilder.or(
                                        criteriaBuilder.like(patientRoot.get("firstname"), "%" + name + "%"),
                                        criteriaBuilder.like(patientRoot.get("lastname"), "%" + name + "%")
                                ),
                                criteriaBuilder.equal(root.get("patient").get("id"), patientRoot.get("id")))
                );
                return criteriaBuilder.exists(patientCriteria);
            }
        };
    }
}
