package com.blubank.doctorappointment.domain.entity;

import com.blubank.doctorappointment.domain.predicates.IsNotLessThan30MinDuration;
import com.blubank.doctorappointment.domain.predicates.IsNullMedicalNo;
import com.blubank.doctorappointment.domain.predicates.IsValidStartAndEndTime;
import com.blubank.doctorappointment.domain.vo.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;

/**
 * @author a.ariani
 */
public class Doctor {
    private ID id;
    private MedicalNo medicalNo;
    private FullName fullName;
    private List<Appointment> appointments;

    private Doctor(ID id, MedicalNo medicalNo, FullName fullName, List<Appointment> appointments) {
        this.id = id;
        this.medicalNo = medicalNo;
        this.fullName = fullName;

        this.appointments = appointments;
    }

    private Doctor(ID id, MedicalNo medicalNo, FullName fullName) {
        this.id = id;
        setMedicalNo(medicalNo);
        setFullName(fullName);
    }

    private Doctor(ID id, MedicalNo medicalNo, FullName fullName,List<Appointment> appointments) {
        this.id = id;
        setMedicalNo(medicalNo);
        setFullName(fullName);
        this
    }
    public static Doctor of(Long id,Long medicalNo,String fullName) {
        return new Doctor(ID.of(id), MedicalNo.of(medicalNo), FullName.of(fullName));
    }

    public static Doctor of(Long id ,Long medicalNo,String fullName,List<OpenTime> openTimes) {
        return new Doctor(ID.of(id), MedicalNo.of(medicalNo), FullName.of(fullName), openTimes);
    }
    public static Doctor of(Long id ,Long medicalNo,String fullName,List<Appointment> appointments) {
        return new Doctor(ID.of(id), MedicalNo.of(medicalNo), FullName.of(fullName), appointments);
    }


    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public MedicalNo getMedicalNo() {
        return medicalNo;
    }

    public void setMedicalNo(MedicalNo medicalNo) {
        new IsNullMedicalNo().check(medicalNo);
        this.medicalNo = medicalNo;
    }

    public FullName getFullName() {
        return fullName;
    }

    public void setFullName(FullName fullName) {
        this.fullName = fullName;
    }



    public void addOpenTimes(OpenTime openTime) {
        new IsValidStartAndEndTime().check(openTime);
        if (new IsNotLessThan30MinDuration().test(openTime)) {
            LocalDate visitDate = openTime.getVisitDate().getVisitDate();
            LocalTime start = openTime.getTimeDuration().getStart();
            LocalTime end = openTime.getTimeDuration().getEnd();
            while (start.isBefore(end)) {
                LocalTime plus = LocalTime.of(start.getHour(),
                        start.getMinute()).plus(30, ChronoUnit.MINUTES);
                openTimes.add(OpenTime.of(visitDate, start,
                        plus));
                start = plus;
            }
        }
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void addAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return id.equals(doctor.id) &&
                medicalNo.equals(doctor.medicalNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, medicalNo);
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", medicalNo=" + medicalNo +
                ", fullName=" + fullName +
                '}';
    }
}
