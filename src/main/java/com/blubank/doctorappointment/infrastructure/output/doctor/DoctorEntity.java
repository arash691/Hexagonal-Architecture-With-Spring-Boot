package com.blubank.doctorappointment.infrastructure.output.doctor;

import com.blubank.doctorappointment.domain.entity.Doctor;
import com.blubank.doctorappointment.domain.vo.OpenTime;
import com.blubank.doctorappointment.infrastructure.output.BaseEntity;
import com.blubank.doctorappointment.infrastructure.output.appointment.AppointmentEntity;
import com.blubank.doctorappointment.infrastructure.output.opentime.OpenTimeEntity;
import javax.persistence.*;
import java.util.*;
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
    @OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private Set<OpenTimeEntity> openTimes = new HashSet<>();
    @OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private Set<AppointmentEntity> appointments = new HashSet<>();

    public static DoctorEntity from(Doctor doctor) {
        DoctorEntity doctorEntity = new DoctorEntity();
        doctorEntity.setFullName(doctor.getFullName().getFullName());
        doctorEntity.setMedicalNo(doctor.getMedicalNo().getMedicalNo());
        Set<OpenTimeEntity> openTimeEntities = new HashSet<>();
        if (doctor.getOpenTimes() != null && !doctor.getOpenTimes().isEmpty()) {
            for (OpenTime openTime : doctor.getOpenTimes()) {
                OpenTimeEntity openTimeEntity = new OpenTimeEntity();
                openTimeEntity.setVisitDate(openTime.getVisitDate().getVisitDate());
                openTimeEntity.setDoctor(doctorEntity);
                openTimeEntity.setStartTime(openTime.getTimeDuration().getStart());
                openTimeEntity.setEndTime(openTime.getTimeDuration().getEnd());
                openTimeEntities.add(openTimeEntity);
            }
            doctorEntity.setOpenTimes(openTimeEntities);
        }
        doctorEntity.setId(doctor.getId().getId());
        return doctorEntity;
    }

    public Doctor toDomain() {
        return Doctor.of(this.getId(), this.getMedicalNo(), this.getFullName(),
                openTimes.stream().map(OpenTimeEntity::toDomain).collect(Collectors.toList()),
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

    public Set<OpenTimeEntity> getOpenTimes() {
        return openTimes;
    }

    public void setOpenTimes(Set<OpenTimeEntity> openTimes) {
        this.openTimes = openTimes;
    }

    public Set<AppointmentEntity> getAppointments() {
        return appointments;
    }

    public void setAppointments(Set<AppointmentEntity> appointments) {
        this.appointments = appointments;
    }

    public void removeTimeTable(OpenTimeEntity timeTable) {
        this.openTimes.remove(timeTable);
        timeTable.setDoctor(null);
    }

    public void removeAllTimeTables(Set<OpenTimeEntity> timeTables) {
        Iterator<OpenTimeEntity> iterator = timeTables.iterator();
        while (iterator.hasNext()) {
            OpenTimeEntity openTimeEntity = iterator.next();
            iterator.remove();
            openTimeEntity.setDoctor(null);
        }
    }

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
