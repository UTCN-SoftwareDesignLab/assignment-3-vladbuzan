package com.assignment3.assignment3;

import com.assignment3.assignment3.consultation.dto.ConsultationRequestDto;
import com.assignment3.assignment3.consultation.model.Consultation;
import com.assignment3.assignment3.patient.dto.PatientDisplayDto;
import com.assignment3.assignment3.patient.dto.PatientRequestDto;
import com.assignment3.assignment3.patient.model.Patient;
import com.assignment3.assignment3.user.model.ERole;
import com.assignment3.assignment3.user.model.Role;
import com.assignment3.assignment3.user.model.User;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import static java.util.stream.Collectors.toList;
public class TestCreationFactory {

    @SuppressWarnings("all")
    public static <T> List<T> listOf(Class cls) {
        return listOf(cls, (Object[]) null);
    }

    @SuppressWarnings("all")
    public static <T> List<T> listOf(Class cls,Object... parameters){
        int nrElements = new Random().nextInt(10) + 5;
        Supplier<?> supplier;
        if(cls.equals(PatientDisplayDto.class)){
            supplier = TestCreationFactory::newPatientDisplayDto;
        } else if(cls.equals(Patient.class)){
            supplier = TestCreationFactory::newPatient;
        } else {
            supplier = () -> new String("You failed.");
        }
        Supplier<?> finalSupplier = supplier;
        return IntStream.range(0, nrElements).mapToObj(i ->
                (T) finalSupplier.get()
        ).collect(Collectors.toSet()) // remove duplicates in case of Long for example
                .stream().collect(toList());
    }

    public static PatientDisplayDto newPatientDisplayDto() {
        return PatientDisplayDto.builder()
                .id(randomLong())
                .birthdate(randomString())
                .address(randomString())
                .cnp(randomString())
                .firstname(randomString())
                .lastname(randomString())
                .build();
    }

    public static PatientRequestDto newPatientRequestDto() {
        return PatientRequestDto.builder()
                .address(randomString())
                .birthdate(new Date())
                .cnp(randomString())
                .firstname(randomString())
                .lastname(randomString())
                .build();
    }

    public static Patient newPatient() {
        return Patient.builder()
                .birthdate(new Date())
                .cnp(randomString())
                .firstname(randomString())
                .lastname(randomString())
                .build();
    }

    public static User newUser() {
        return User.builder()
                .id(randomLong())
                .email(randomEmail())
                .role(newRole())
                .password(randomString())
                .username(randomString())
                .firstname(randomString())
                .lastname(randomString())
                .build();
    }

    public static Role newRole() {
        return Role.builder()
                .id(randomLong())
                .name(ERole.DOCTOR)
                .build();
    }
    public static ConsultationRequestDto newConsultationRequestDto() {
        return ConsultationRequestDto.builder()
                .date(new Date())
                .description(randomString())
                .doctorId(randomLong())
                .patientId(randomLong())
                .duration(randomBoundedInt(100))
                .build();
    }

    public static Consultation newConsultation() {
        return Consultation.builder()
                .startDate(new Date())
                .endDate(new Date())
                .doctor(newUser())
                .patient(newPatient())
                .description(randomString())
                .build();
    }
    public static String randomEmail() {
        return randomString() + "@" + randomString() + ".com";
    }


    public static byte[] randomBytes() {
        byte[] bytes = new byte[Math.toIntExact(randomLong())];
        new Random().nextBytes(bytes);
        return bytes;
    }

    public static long randomLong() {
        return new Random().nextInt(1999);
    }

    public static Boolean randomBoolean() {
        return new Random().nextBoolean();
    }

    public static Double randomDouble() {
        return new Random().nextDouble();
    }

    public static Integer randomInteger() {
        return new Random().nextInt();
    }

    public static int randomBoundedInt(int upperBound) {
        return new Random().nextInt(upperBound);
    }


    public static String randomString() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
