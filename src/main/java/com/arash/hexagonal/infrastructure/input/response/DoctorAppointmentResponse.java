package com.arash.hexagonal.infrastructure.input.response;

import com.arash.hexagonal.domain.vo.Appointment;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author a.ariani
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DoctorAppointmentResponse {
    private LocalDate visitDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private String fullName;
    private String phoneNumber;

    public static List<DoctorAppointmentResponse> from(List<Appointment> appointments) {
        List<DoctorAppointmentResponse> doctorAppointmentResponses = new ArrayList<>();
        for (Appointment appointment : appointments) {
            DoctorAppointmentResponse doctorAppointmentResponse = new DoctorAppointmentResponse();
            doctorAppointmentResponse.setVisitDate(appointment.getOpenTime().getVisitDate().getVisitDate());
            doctorAppointmentResponse.setStartTime(appointment.getOpenTime().getTimeDuration().getStart());
            doctorAppointmentResponse.setEndTime(appointment.getOpenTime().getTimeDuration().getEnd());
            doctorAppointmentResponse.setFullName(appointment.getPatient() != null && appointment.getPatient().getFullName() != null
                    ? appointment.getPatient().getFullName().getFullName() : null);
            doctorAppointmentResponse.setPhoneNumber(appointment.getPatient() != null && appointment.getPatient().getPhoneNumber() != null ?
                    appointment.getPatient().getPhoneNumber().value() : null);
            doctorAppointmentResponses.add(doctorAppointmentResponse);
        }
        return doctorAppointmentResponses;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
