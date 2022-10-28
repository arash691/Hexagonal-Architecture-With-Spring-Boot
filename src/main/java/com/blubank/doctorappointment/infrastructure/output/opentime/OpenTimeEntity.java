package com.blubank.doctorappointment.infrastructure.output.opentime;

import com.blubank.doctorappointment.domain.vo.OpenTime;
import com.blubank.doctorappointment.infrastructure.output.BaseEntity;
import com.blubank.doctorappointment.infrastructure.output.doctor.DoctorEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

/**
 * @author a.ariani
 */
//@Table(name = "open_time_tbl",uniqueConstraints ={@UniqueConstraint(name = "UC_UNIQUE_TIME_PER_DOC",columnNames = {"visit_date","start_time","end_time","doctor_id"})})
//@Entity
public class OpenTimeEntity extends BaseEntity {
    @Column(name = "visit_date", nullable = false)
    private LocalDate visitDate;
    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;
    @Column(name = "end_time", nullable = false)
    private LocalTime endTime;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(nullable = false, referencedColumnName = "id", name = "doctor_id")
    private DoctorEntity doctor;

    public static OpenTimeEntity from(DoctorEntity doctor, OpenTime openTime) {
        OpenTimeEntity openTimeEntity = new OpenTimeEntity();
        openTimeEntity.setVisitDate(openTime.getVisitDate().getVisitDate());
        openTimeEntity.setStartTime(openTime.getTimeDuration().getStart());
        openTimeEntity.setEndTime(openTime.getTimeDuration().getEnd());
        openTimeEntity.setDoctor(doctor);
        return openTimeEntity;
    }

/*    public OpenTime toDomain() {
        return OpenTime.of(ID.of(getId()), getVisitDate(), getStartTime(), getEndTime()
                , Doctor.of(getDoctor().getId(), getDoctor().getMedicalNo(), getDoctor().getFullName()));
    }*/

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

    public DoctorEntity getDoctor() {
        return doctor;
    }

    public void setDoctor(DoctorEntity doctor) {
        this.doctor = doctor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OpenTimeEntity that = (OpenTimeEntity) o;
        return visitDate.equals(that.visitDate) &&
                startTime.equals(that.startTime) &&
                endTime.equals(that.endTime) &&
                doctor.equals(that.doctor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(visitDate, startTime, endTime, doctor);
    }
}
