package com.arash.hexagonal.domain.entity;

import com.arash.hexagonal.domain.vo.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

/**
 * @author a.ariani
 */
public class Doctor {
    private Id id;
    private MedicalNumber medicalNumber;
    private FullName fullName;
    private List<Appointment> appointments;

    private Doctor(Id id) {
        this.id = id;
    }

    private Doctor(Id id, MedicalNumber medicalNumber, FullName fullName) {
        this.id = id;
        setMedicalNo(medicalNumber);
        setFullName(fullName);
    }

    private Doctor(Id id, MedicalNumber medicalNumber, FullName fullName, List<Appointment> appointments) {
        this.id = id;
        setMedicalNo(medicalNumber);
        setFullName(fullName);
        this.appointments = appointments;
    }

    public static Doctor of(Long id, Long medicalNo, String fullName) {
        return new Doctor(new Id(id), new MedicalNumber(medicalNo), new FullName(fullName));
    }

    public static Doctor of(Long id, Long medicalNo, String fullName, List<Appointment> appointments) {
        return new Doctor(new Id(id), new MedicalNumber(medicalNo), new FullName(fullName), appointments);
    }

    public static Doctor of(Long doctorId) {
        return new Doctor(new Id(doctorId));
    }

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public MedicalNumber getMedicalNumber() {
        return medicalNumber;
    }

    public void setMedicalNo(MedicalNumber medicalNumber) {
        this.medicalNumber = medicalNumber;
    }

    public FullName getFullName() {
        return fullName;
    }

    public void setFullName(FullName fullName) {
        this.fullName = fullName;
    }

    public void addOpenTime(OpenTime openTime) {

        LocalDate visitDate = openTime.visitDate().value();
        LocalTime begin = openTime.timeDuration().begin();
        LocalTime end = openTime.timeDuration().end();

        while (begin.isBefore(end)) {
            LocalTime plus = LocalTime.of(begin.getHour(), begin.getMinute()).plusMinutes(30);

            this.appointments.add(new Appointment(this, null, new OpenTime(new VisitDate(visitDate), new TimeDuration(begin, plus)), 0));
            begin = plus;
        }
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return id.equals(doctor.id) &&
                medicalNumber.equals(doctor.medicalNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, medicalNumber);
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", medicalNo=" + medicalNumber +
                ", fullName=" + fullName +
                '}';
    }
}
