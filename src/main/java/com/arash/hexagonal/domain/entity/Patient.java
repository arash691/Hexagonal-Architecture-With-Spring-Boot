package com.arash.hexagonal.domain.entity;

import com.arash.hexagonal.domain.vo.Appointment;
import com.arash.hexagonal.domain.vo.FullName;
import com.arash.hexagonal.domain.vo.ID;
import com.arash.hexagonal.domain.vo.PhoneNumber;

import java.util.List;
import java.util.Objects;

/**
 * @author a.ariani
 */
public class Patient {
    private ID id;
    private FullName fullName;
    private PhoneNumber phoneNumber;
    private List<Appointment> appointments;

    private Patient(ID id, FullName fullName, PhoneNumber phoneNumber) {
        this.id = id;
        setFullName(fullName);
        setPhoneNumber(phoneNumber);
    }

    private Patient(ID id, FullName fullName, PhoneNumber phoneNumber, List<Appointment> appointments) {
        this.id = id;
        setFullName(fullName);
        setPhoneNumber(phoneNumber);
        this.appointments = appointments;
    }

    public Patient(ID patientId) {
        this.id = patientId;
    }

    public static Patient of(String fullName, String phoneNumber) {
        return new Patient(null, new FullName(fullName), new PhoneNumber(phoneNumber));
    }

    public static Patient of(Long id, String fullName, String phoneNumber) {
        return new Patient(ID.of(id), new FullName(fullName), new PhoneNumber(phoneNumber));
    }

    public static Patient of(Long id, String fullName, String phoneNumber, List<Appointment> appointments) {
        return new Patient(ID.of(id), new FullName(fullName), new PhoneNumber(phoneNumber), appointments);
    }

    public static Patient of(Long patientId) {
        return new Patient(ID.of(patientId));
    }

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public FullName getFullName() {
        return fullName;
    }

    public void setFullName(FullName fullName) {
        this.fullName = fullName;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return id.equals(patient.id) &&
                phoneNumber.equals(patient.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, phoneNumber);
    }
}