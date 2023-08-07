package com.arash.hexagonal.infrastructure.output.appointment;

import com.arash.hexagonal.domain.entity.Doctor;
import com.arash.hexagonal.domain.entity.Patient;
import com.arash.hexagonal.domain.vo.Appointment;
import com.arash.hexagonal.domain.vo.OpenTime;
import com.arash.hexagonal.domain.vo.TimeDuration;
import com.arash.hexagonal.domain.vo.VisitDate;
import com.arash.hexagonal.infrastructure.output.doctor.DoctorEntity;
import com.arash.hexagonal.infrastructure.output.patient.PatientEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

/**
 * @author a.ariani
 */
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
        value = {"createdAt", "updatedAt"}
)
@Table(name = "appointment_tbl")
@Entity
public class AppointmentEntity implements Serializable {
    @EmbeddedId
    private AppointmentPK id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @MapsId("doctorId")
    @JoinColumn(name = "doctor_id", nullable = false)
    private DoctorEntity doctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private PatientEntity patient;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private Instant createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private Instant updatedAt;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @Version
    @Column(columnDefinition = "integer DEFAULT 0", nullable = false)
    private Integer version;


    public AppointmentEntity() {
        this.isActive = true;
    }

    public AppointmentEntity(DoctorEntity doctor, PatientEntity patient, LocalDate visitDate,
                             LocalTime startTime, LocalTime endTime, Integer version) {
        this.id = new AppointmentPK(doctor.getId(), visitDate, startTime, endTime);
        this.doctor = doctor;
        this.patient = patient;
        this.isActive = true;
        this.version = version;
    }

    public static AppointmentEntity from(DoctorEntity doctorEntity, PatientEntity patientEntity,
                                         OpenTime openTime, Integer version) {
        return new AppointmentEntity(doctorEntity, patientEntity, openTime.visitDate().value(),
                openTime.timeDuration().begin(),
                openTime.timeDuration().end(), version);
    }

    public AppointmentPK getId() {
        return id;
    }

    public void setId(AppointmentPK id) {
        this.id = id;
    }

    public DoctorEntity getDoctor() {
        return doctor;
    }

    public void setDoctor(DoctorEntity doctor) {
        this.doctor = doctor;
    }

    public PatientEntity getPatient() {
        return patient;
    }

    public void setPatient(PatientEntity patient) {
        this.patient = patient;
    }


    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Integer getVersion() {
        return version;
    }

    public Appointment toDomain() {
        return new Appointment(Doctor.of(doctor.getId()), patient != null ? Patient.of(patient.getId()) : null,
                new OpenTime(new VisitDate(id.getVisitDate()), new TimeDuration(id.getStartTime(), id.getEndTime())), version);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppointmentEntity that = (AppointmentEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
