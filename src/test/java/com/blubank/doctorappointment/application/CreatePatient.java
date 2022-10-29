package com.blubank.doctorappointment.application;

import com.blubank.doctorappointment.application.ports.input.DoctorServicePort;
import com.blubank.doctorappointment.application.ports.input.PatientServicePort;
import com.blubank.doctorappointment.domain.entity.Doctor;
import com.blubank.doctorappointment.domain.entity.Patient;
import com.blubank.doctorappointment.domain.vo.OpenTime;
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
        doctor = doctorService.createOpenTime(doctor.getId(), OpenTime.of(LocalDate.of(2022, 10, 10),
                LocalTime.of(9, 0), LocalTime.of(9, 30)));
        patient = patientServicePort.createAppointment(
                doctor,
                Patient.of("patient patient", "09111234567"),
                doctor.getAppointments().get(0).getOpenTime()
        );
    }

    @Then("A new patient is created")
    public void a_new_Patient_is_created() {
        assertNotNull(patient);
        assertEquals(patient.getPhoneNumber().getPhoneNumber(), "09111234567");
        assertEquals(patient.getFullName().getFullName(), "patient patient");
        assertNotNull(patient.getAppointments());
        assertEquals(patient.getAppointments().size(), 1);
        OpenTime openTime = patient.getAppointments().get(0).getOpenTime();
        assertEquals(openTime.getVisitDate().getVisitDate(), LocalDate.of(2022, 10, 10));
        assertEquals(openTime.getTimeDuration().getStart(), LocalTime.of(9, 0));
        assertEquals(openTime.getTimeDuration().getEnd(), LocalTime.of(9, 30));

    }
}
