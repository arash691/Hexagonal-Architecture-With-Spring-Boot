package com.blubank.doctorappointment.application.query;

import com.blubank.doctorappointment.domain.vo.OpenTime;
import com.blubank.doctorappointment.domain.vo.*;
import com.fasterxml.jackson.annotation.JsonInclude;


import java.util.List;
import java.util.stream.Collectors;

/**
 * @author a.ariani
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DoctorOpenAndTakenTime {
    private final VisitDate visitDate;
    private final TimeDuration timeDuration;
    private final FullName fullName;
    private final PhoneNumber phoneNumber;

    private DoctorOpenAndTakenTime(VisitDate visitDate, TimeDuration timeDuration, FullName fullName, PhoneNumber phoneNumber) {
        this.visitDate = visitDate;
        this.timeDuration = timeDuration;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
    }

    public static List<DoctorOpenAndTakenTime> from(List<OpenTime> openTimes,List<Appointment> appointments){
        List<DoctorOpenAndTakenTime> collect = openTimes.stream().map(openTime -> new DoctorOpenAndTakenTime(openTime.getVisitDate(),
                openTime.getTimeDuration(), null, null))
                .collect(Collectors.toList());
                collect.addAll(appointments.stream().map(appointment -> new DoctorOpenAndTakenTime(appointment.getOpenTime().getVisitDate()
                        , appointment.getOpenTime().getTimeDuration(),
                        appointment.getPatient().getFullName(), appointment.getPatient().getPhoneNumber())).collect(Collectors.toList()));
        return collect;
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
