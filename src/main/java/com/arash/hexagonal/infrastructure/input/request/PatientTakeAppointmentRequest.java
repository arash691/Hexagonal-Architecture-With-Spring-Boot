package com.arash.hexagonal.infrastructure.input.request;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Created by arash on 27.10.22.
 */

public class PatientTakeAppointmentRequest {
    private Long doctorId;
    private String phoneNumber;
    private String name;
    private VisitDateInfo visitDateInfo;

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public VisitDateInfo getVisitDateInfo() {
        return visitDateInfo;
    }

    public void setVisitDateInfo(VisitDateInfo visitDateInfo) {
        this.visitDateInfo = visitDateInfo;
    }

    public static class VisitDateInfo {
        private LocalDate visitDate;
        private LocalTime startTime;
        private LocalTime endTime;

        public LocalDate getVisitDate() {
            return visitDate;
        }

        public void setVisitDate(LocalDate visitDate) {
            this.visitDate = visitDate;
        }

        public LocalTime getStartTime() {
            return startTime;
        }

        public void setStartTime(LocalTime startTime) {
            this.startTime = startTime;
        }

        public LocalTime getEndTime() {
            return endTime;
        }

        public void setEndTime(LocalTime endTime) {
            this.endTime = endTime;
        }
    }
}
