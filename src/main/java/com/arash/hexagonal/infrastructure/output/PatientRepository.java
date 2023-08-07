package com.arash.hexagonal.infrastructure.output;

import com.arash.hexagonal.domain.entity.Doctor;
import com.arash.hexagonal.domain.entity.Patient;
import com.arash.hexagonal.domain.vo.Appointment;
import com.arash.hexagonal.domain.vo.OpenTime;
import com.arash.hexagonal.domain.vo.TimeDuration;
import com.arash.hexagonal.domain.vo.VisitDate;
import com.arash.hexagonal.infrastructure.output.patient.PatientEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<PatientEntity, Long> {
    @EntityGraph(attributePaths = {"appointments"})
    Optional<PatientEntity> findDetailById(Long id);

    @Query(value = "SELECT ap.id.visitDate AS visitDate,ap.id.startTime AS startTime " +
            ",ap.id.endTime AS endTime,ap.doctor.id AS doctorId," +
            "ap.version AS version,p.id AS patientId" +
            " FROM PatientEntity p INNER JOIN AppointmentEntity ap " +
            "ON p.id=ap.patient.id WHERE p.phoneNumber=?1")
    List<PatientAppointment> findAppointmentByPhoneNumber(String phoneNumber);

    interface PatientAppointment {
        static Appointment toDomain(PatientAppointment patientAppointment) {
            return new Appointment(Doctor.of(patientAppointment.getDoctorId()),
                    Patient.of(patientAppointment.getPatientId()),
                    new OpenTime(new VisitDate(patientAppointment.getVisitDate()), new TimeDuration(patientAppointment.getStartTime(), patientAppointment.getEndTime())),
                    patientAppointment.getVersion());
        }

        LocalDate getVisitDate();

        LocalTime getStartTime();

        LocalTime getEndTime();

        Long getDoctorId();

        Long getPatientId();

        Integer getVersion();


    }
}
