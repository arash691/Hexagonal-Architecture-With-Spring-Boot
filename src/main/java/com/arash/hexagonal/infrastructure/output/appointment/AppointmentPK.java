package com.arash.hexagonal.infrastructure.output.appointment;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

/**
 * @author a.ariani
 */
@Embeddable
public class AppointmentPK implements Serializable {
    @Column(name = "doctor_id")
    private Long doctorId;
    @Column(name = "visit_date", nullable = false)
    private LocalDate visitDate;
    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;
    @Column(name = "end_time", nullable = false)
    private LocalTime endTime;

    public AppointmentPK() {
    }

    public AppointmentPK(Long doctorId, LocalDate visitDate, LocalTime startTime, LocalTime endTime) {
        this.doctorId = doctorId;
        this.visitDate = visitDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public LocalDate getVisitDate() {
        return visitDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppointmentPK that = (AppointmentPK) o;
        return doctorId.equals(that.doctorId) &&
                visitDate.equals(that.visitDate) &&
                startTime.equals(that.startTime) &&
                endTime.equals(that.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(doctorId, visitDate, startTime, endTime);
    }
}
