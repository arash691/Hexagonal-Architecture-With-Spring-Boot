package com.blubank.doctorappointment.application.dto;

import com.blubank.doctorappointment.domain.vo.*;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author a.ariani
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DoctorAppointment {
    private final VisitDate visitDate;
    private final TimeDuration timeDuration;
    private final FullName fullName;
    private final PhoneNumber phoneNumber;

    private DoctorAppointment(VisitDate visitDate, TimeDuration timeDuration,
                              FullName fullName
            , PhoneNumber phoneNumber) {
        this.visitDate = visitDate;
        this.timeDuration = timeDuration;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
    }

    public static DoctorAppointment from(Appointment appointment) {
        return new DoctorAppointment(appointment.getOpenTime().getVisitDate()
                , appointment.getOpenTime().getTimeDuration(),
                appointment.getPatient() != null ? appointment.getPatient().getFullName() : null,
                appointment.getPatient() != null ? appointment.getPatient().getPhoneNumber() : null);
    }


    public VisitDate getVisitDate() {
        return visitDate;
    }

    public TimeDuration getTimeDuration() {
        return timeDuration;
    }

    public FullName getFullName() {
        return fullName;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }
}
