package com.blubank.doctorappointment.infrastructure.input.response;

import com.blubank.doctorappointment.domain.entity.Doctor;
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
public class DoctorResponse {
    private Long medicalNo;
    private String fullName;
    private List<AppointmentResponse> appointments = new ArrayList<>();

    public static DoctorResponse from(Doctor doctor) {
        DoctorResponse doctorResponse = new DoctorResponse();
        doctorResponse.setMedicalNo(doctor.getMedicalNo().getMedicalNo());
        doctorResponse.setFullName(doctor.getFullName().getFullName());
        List<AppointmentResponse> appointmentResponses = new ArrayList<>();
        for (Appointment appointment : doctor.getAppointments()) {
            AppointmentResponse appointmentResponse = new AppointmentResponse();
            appointmentResponse.setDate(appointment.getOpenTime().getVisitDate().getVisitDate());
            appointmentResponse.setStartTime(appointment.getOpenTime().getTimeDuration().getStart());
            appointmentResponse.setEndTime(appointment.getOpenTime().getTimeDuration().getEnd());
            appointmentResponses.add(appointmentResponse);
        }
        doctorResponse.setAppointments(appointmentResponses);
        return doctorResponse;
    }

    public Long getMedicalNo() {
        return medicalNo;
    }

    public void setMedicalNo(Long medicalNo) {
        this.medicalNo = medicalNo;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<AppointmentResponse> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<AppointmentResponse> appointments) {
        this.appointments = appointments;
    }

    public static class AppointmentResponse {
        private LocalDate date;
        private LocalTime startTime;
        private LocalTime endTime;

        public LocalDate getDate() {
            return date;
        }

        public void setDate(LocalDate date) {
            this.date = date;
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
