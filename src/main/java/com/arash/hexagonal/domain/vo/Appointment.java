package com.arash.hexagonal.domain.vo;

import com.arash.hexagonal.domain.entity.Doctor;
import com.arash.hexagonal.domain.entity.Patient;

import java.util.Objects;

/**
 * @author a.ariani
 */
public class Appointment {
    private final Doctor doctor;
    private final Patient patient;
    private final OpenTime openTime;
    private final Integer version;

    private Appointment(Doctor doctor, Patient patient, OpenTime openTime, Integer version) {
        this.doctor = doctor;
        this.patient = patient;
        this.openTime = openTime;
        this.version = version;
    }

    public static Appointment of(Doctor doctor, Patient patient, OpenTime openTime, Integer version) {
        return new Appointment(doctor, patient, openTime, version);
    }

    public static Appointment of(Long doctorId, Long patientId, OpenTime openTime, Integer version) {
        return new Appointment(Doctor.of(doctorId), Patient.of(patientId), openTime, version);
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

    public Integer getVersion() {
        return version;
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
