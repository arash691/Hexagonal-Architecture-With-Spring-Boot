package com.blubank.doctorappointment.application.query;

import com.blubank.doctorappointment.domain.entity.Doctor;
import com.blubank.doctorappointment.domain.vo.TimeDuration;
import com.blubank.doctorappointment.domain.vo.VisitDate;

import java.util.List;

/**
 * @author a.ariani
 */
public class OpenTimeToTake {
    private final VisitDate visitDate;
    private final TimeDuration timeDuration;
    private final Doctor doctor;

    public OpenTimeToTake(VisitDate visitDate, TimeDuration timeDuration, Doctor doctor) {
        this.visitDate = visitDate;
        this.timeDuration = timeDuration;
        this.doctor = doctor;
    }

    public VisitDate getVisitDate() {
        return visitDate;
    }

    public TimeDuration getTimeDuration() {
        return timeDuration;
    }

    public Doctor getDoctor() {
        return doctor;
    }
}
