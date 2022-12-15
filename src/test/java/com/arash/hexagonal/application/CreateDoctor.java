package com.arash.hexagonal.application;

import com.arash.hexagonal.application.ports.input.DoctorServicePort;
import com.arash.hexagonal.domain.entity.Doctor;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Created by arash on 29.10.22.
 */

public class CreateDoctor {

    private Doctor doctor;
    @Autowired
    private DoctorServicePort doctorService;

    @Given("I provide all required data to create a doctor")
    public void I_Provide_All_Required_Data_To_Create_A_Doctor() {
        doctor = doctorService.create(Doctor.of(1L, 123456L, "arash ariani"));
    }

    @Then("A new doctor is created")
    public void a_new_Doctor_is_created() {
        assertNotNull(doctor);
        assertEquals(1L, doctor.getId().getId());
        assertEquals(123456L, doctor.getMedicalNo().getMedicalNo());
        assertEquals("arash ariani", doctor.getFullName().getFullName());
    }


}
