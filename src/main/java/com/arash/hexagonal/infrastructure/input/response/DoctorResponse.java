package com.arash.hexagonal.infrastructure.input.response;

import com.arash.hexagonal.domain.entity.Doctor;
import com.arash.hexagonal.domain.vo.Appointment;
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
        doctorResponse.setMedicalNo(doctor.getMedicalNumber().value());
        doctorResponse.setFullName(doctor.getFullName().value());
        List<AppointmentResponse> appointmentResponses = new ArrayList<>();
        for (Appointment appointment : doctor.getAppointments()) {
            AppointmentResponse appointmentResponse = new AppointmentResponse();
            appointmentResponse.setDate(appointment.openTime().visitDate().value());
            appointmentResponse.setStartTime(appointment.openTime().timeDuration().begin());
            appointmentResponse.setEndTime(appointment.openTime().timeDuration().end());
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
