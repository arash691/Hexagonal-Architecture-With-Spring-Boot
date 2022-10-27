package com.blubank.doctorappointment.infrastructure.output.appointment;

import com.blubank.doctorappointment.domain.vo.Appointment;
import com.blubank.doctorappointment.infrastructure.output.doctor.DoctorEntity;
import com.blubank.doctorappointment.infrastructure.output.patient.PatientEntity;
import com.blubank.doctorappointment.infrastructure.output.opentime.OpenTimeEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

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
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @MapsId("patientId")
    @JoinColumn(name = "patient_id", nullable = false)
    private PatientEntity patient;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @MapsId("openTimeId")
    @JoinColumn(name = "open_time_id", nullable = false)
    private OpenTimeEntity openTime;

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

    public AppointmentEntity(DoctorEntity doctor, PatientEntity patient, OpenTimeEntity openTime) {
        this.id = new AppointmentPK(doctor.getId(), patient.getId(), openTime.getId());
        this.doctor = doctor;
        this.patient = patient;
        this.openTime = openTime;
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

    public OpenTimeEntity getOpenTime() {
        return openTime;
    }

    public void setOpenTime(OpenTimeEntity openTime) {
        this.openTime = openTime;
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

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Appointment toDomain() {
        return Appointment.of(doctor.toDomain(), patient.toDomain(), openTime.toDomain());
    }

}
