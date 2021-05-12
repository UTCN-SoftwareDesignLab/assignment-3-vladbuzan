package com.assignment3.assignment3.consultation;

import com.assignment3.assignment3.consultation.model.Consultation;
import com.assignment3.assignment3.patient.model.Patient;
import com.assignment3.assignment3.user.model.User;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.Date;

public class ConsultationSpecifications {
    public static Specification<Consultation> similarDoctor(String name) {
        return (root, query, criteriaBuilder) -> {
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
        };
    }

    public static Specification<Consultation> similarPatient(String name) {
        return (root, query, criteriaBuilder) -> {
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
        };
    }

    public static Specification<Consultation> dateBetweenConsultationDates(Date date, Long medicId) {
        return (root, query, criteriaBuilder) -> {
            Subquery<User> userSubQuery = query.subquery(User.class);
            Root<User> userRoot = userSubQuery.from(User.class);
            Subquery<Consultation> consultationCriteria = query.subquery(Consultation.class)
                    .select(root).where(criteriaBuilder.and(
                            criteriaBuilder.and(
                                criteriaBuilder.lessThan(root.get("startDate"), date),
                                criteriaBuilder.greaterThan(root.get("endDate"), date)
                            ),
                            criteriaBuilder.and(criteriaBuilder.equal(root.get("doctor").get("id"), userRoot.get("id"))
                            ,criteriaBuilder.equal(userRoot.get("id"), medicId))));
            return criteriaBuilder.exists(consultationCriteria);
        };
    }
}
