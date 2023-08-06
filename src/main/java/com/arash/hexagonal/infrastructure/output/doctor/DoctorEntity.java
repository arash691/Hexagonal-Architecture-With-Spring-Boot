package com.arash.hexagonal.infrastructure.output.doctor;

import com.arash.hexagonal.domain.entity.Doctor;
import com.arash.hexagonal.domain.vo.Appointment;
import com.arash.hexagonal.infrastructure.output.BaseEntity;
import com.arash.hexagonal.infrastructure.output.appointment.AppointmentEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author a.ariani
 */
@Table(name = "doctor_tbl")
@Entity
public class DoctorEntity extends BaseEntity {
    @Column(name = "medical_no", nullable = false)
    private Long medicalNo;
    @Column(name = "full_name")
    private String fullName;
    /*    @OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
        private Set<OpenTimeEntity> openTimes = new HashSet<>();*/
    @OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private Set<AppointmentEntity> appointments = new HashSet<>();

    public static DoctorEntity from(Doctor doctor) {
        DoctorEntity doctorEntity = new DoctorEntity();
        doctorEntity.setId(doctor.getId() != null ? doctor.getId().getId() : null);
        doctorEntity.setFullName(doctor.getFullName().value());
        doctorEntity.setMedicalNo(doctor.getMedicalNumber().value());
        Set<AppointmentEntity> appointmentEntities = new HashSet<>();
        if (doctor.getAppointments() != null && !doctor.getAppointments().isEmpty()) {
            for (Appointment appointment : doctor.getAppointments()) {
                appointmentEntities.add(AppointmentEntity.from(doctorEntity, null, appointment.getOpenTime(), appointment.getVersion()));
            }
            doctorEntity.setAppointments(appointmentEntities);
        }
        doctorEntity.setId(doctor.getId().getId());
        return doctorEntity;
    }

    public Doctor toDomain() {
        return Doctor.of(this.getId(), this.getMedicalNo(), this.getFullName(),
                appointments.stream().map(AppointmentEntity::toDomain).collect(Collectors.toList())
        );
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
/*
    public Set<OpenTimeEntity> getOpenTimes() {
        return openTimes;
    }

    public void setOpenTimes(Set<OpenTimeEntity> openTimes) {
        this.openTimes = openTimes;
    }*/

    public Set<AppointmentEntity> getAppointments() {
        return appointments;
    }

    public void setAppointments(Set<AppointmentEntity> appointments) {
        this.appointments = appointments;
    }

  /*  public void removeTimeTable(OpenTimeEntity timeTable) {
        this.openTimes.remove(timeTable);
        timeTable.setDoctor(null);
    }
*/
 /*   public void removeAllTimeTables(Set<OpenTimeEntity> timeTables) {
        Iterator<OpenTimeEntity> iterator = timeTables.iterator();
        while (iterator.hasNext()) {
            OpenTimeEntity openTimeEntity = iterator.next();
            iterator.remove();
            openTimeEntity.setDoctor(null);
        }
    }*/

    public void removeAppointment(AppointmentEntity appointment) {
        this.appointments.remove(appointment);
        appointment.setDoctor(null);
    }

    public void removeAll(Set<AppointmentEntity> appointments) {
        Iterator<AppointmentEntity> iterator = appointments.iterator();
        while (iterator.hasNext()) {
            AppointmentEntity appointmentEntity = iterator.next();
            iterator.remove();
            appointmentEntity.setDoctor(null);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DoctorEntity that = (DoctorEntity) o;
        return medicalNo.equals(that.medicalNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(medicalNo);
    }
}
