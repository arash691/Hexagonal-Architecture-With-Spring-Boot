package com.blubank.doctorappointment.domain.vo;

import com.blubank.doctorappointment.domain.entity.Doctor;
import com.blubank.doctorappointment.domain.entity.Patient;

import java.util.Objects;

/**
 * @author a.ariani
 */
public class Appointment {
    private final Doctor doctor;
    private final Patient patient;
    private final OpenTime openTime;

    private Appointment(Doctor doctor, Patient patient, OpenTime openTime) {
        this.doctor = doctor;
        this.patient = patient;
        this.openTime = openTime;
    }

    public static Appointment of(Doctor doctor,Patient patient,OpenTime openTime) {
        return new Appointment(doctor, patient, openTime);
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public OpenTime getOpenTime() {
        return openTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appointment that = (Appointment) o;
        return doctor.equals(that.doctor) &&
                Objects.equals(patient, that.patient) &&
                openTime.equals(that.openTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(doctor, patient, openTime);
    }
}
