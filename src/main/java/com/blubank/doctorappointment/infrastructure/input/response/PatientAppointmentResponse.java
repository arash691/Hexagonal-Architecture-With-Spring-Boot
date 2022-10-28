package com.blubank.doctorappointment.infrastructure.input.response;

import com.blubank.doctorappointment.domain.vo.Appointment;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by arash on 28.10.22.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PatientAppointmentResponse {

    private LocalDate visitDate;
    private LocalTime startTime;
    private LocalTime endTime;

    public static List<PatientAppointmentResponse> from(List<Appointment> appointments) {
        List<PatientAppointmentResponse> patientAppointmentResponses = new ArrayList<>();
        for (Appointment appointment : appointments) {
            PatientAppointmentResponse patientAppointmentResponse = new PatientAppointmentResponse();
            patientAppointmentResponse.setVisitDate(appointment.getOpenTime().getVisitDate().getVisitDate());
            patientAppointmentResponse.setStartTime(appointment.getOpenTime().getTimeDuration().getStart());
            patientAppointmentResponse.setEndTime(appointment.getOpenTime().getTimeDuration().getEnd());
            patientAppointmentResponses.add(patientAppointmentResponse);
        }
        return patientAppointmentResponses;
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
