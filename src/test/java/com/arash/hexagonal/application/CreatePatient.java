package com.arash.hexagonal.application;

import com.arash.hexagonal.application.ports.input.DoctorServicePort;
import com.arash.hexagonal.application.ports.input.PatientServicePort;
import com.arash.hexagonal.domain.entity.Doctor;
import com.arash.hexagonal.domain.entity.Patient;
import com.arash.hexagonal.domain.vo.OpenTime;
import com.arash.hexagonal.domain.vo.TimeDuration;
import com.arash.hexagonal.domain.vo.VisitDate;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Created by arash on 29.10.22.
 */

public class CreatePatient {

    private Patient patient;

    @Autowired
    private PatientServicePort patientServicePort;

    private Doctor doctor;
    @Autowired
    private DoctorServicePort doctorService;

    @Given("I provide all required data to create a patient")
    public void I_Provide_All_Required_Data_To_Create_A_Patient() {
        doctor = doctorService.create(Doctor.of(100L, 123456L, "doctor doctor"));
        doctor = doctorService.createOpenTime(doctor.getId(),
                new OpenTime(new VisitDate(LocalDate.of(2022, 10, 10)),
                        new TimeDuration(LocalTime.of(9, 0), LocalTime.of(9, 30))));
        patient = patientServicePort.createAppointment(
                doctor,
                Patient.of("patient patient", "09111234567"),
                doctor.getAppointments().get(0).openTime()
        );
    }

    @Then("A new patient is created")
    public void a_new_Patient_is_created() {
        assertNotNull(patient);
        assertEquals(patient.getPhoneNumber().value(), "09111234567");
        assertEquals(patient.getFullName().value(), "patient patient");
        assertNotNull(patient.getAppointments());
        assertEquals(patient.getAppointments().size(), 1);
        OpenTime openTime = patient.getAppointments().get(0).openTime();
        assertEquals(openTime.visitDate().value(), LocalDate.of(2022, 10, 10));
        assertEquals(openTime.timeDuration().begin(), LocalTime.of(9, 0));
        assertEquals(openTime.timeDuration().end(), LocalTime.of(9, 30));
    }
}
