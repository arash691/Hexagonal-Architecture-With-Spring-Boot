package com.blubank.doctorappointment.application.dto;

import com.blubank.doctorappointment.domain.vo.Appointment;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Created by arash on 28.10.22.
 */

public class PatientAppointment {

    private LocalDate visitDate;
    private LocalTime startTime;
    private LocalTime endTime;

    public PatientAppointment(LocalDate visitDate, LocalTime startTime, LocalTime endTime) {
        this.visitDate = visitDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public static PatientAppointment from(Appointment appointment) {
        return new PatientAppointment(appointment.getOpenTime().getVisitDate().getVisitDate(),
                appointment.getOpenTime().getTimeDuration().getStart(),
                appointment.getOpenTime().getTimeDuration().getEnd());
    }

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
