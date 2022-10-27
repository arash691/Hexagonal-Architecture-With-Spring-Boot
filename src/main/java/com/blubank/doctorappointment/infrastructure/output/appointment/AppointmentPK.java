package com.blubank.doctorappointment.infrastructure.output.appointment;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author a.ariani
 */
@Embeddable
public class AppointmentPK implements Serializable {
    @Column(name = "doctor_id")
    private Long doctorId;
    @Column(name = "patient_id")
    private Long patientId;
    @Column(name = "open_time_id")
    private Long openTimeId;

    public AppointmentPK() {
    }

    public AppointmentPK(Long doctorId, Long patientId, Long openTimeId) {
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.openTimeId = openTimeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppointmentPK that = (AppointmentPK) o;
        return doctorId.equals(that.doctorId) &&
                patientId.equals(that.patientId) &&
                openTimeId.equals(that.openTimeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(doctorId, patientId, openTimeId);
    }
}
