package com.arash.hexagonal.infrastructure.output;

import com.arash.hexagonal.domain.entity.Doctor;
import com.arash.hexagonal.domain.entity.Patient;
import com.arash.hexagonal.domain.vo.Appointment;
import com.arash.hexagonal.domain.vo.OpenTime;
import com.arash.hexagonal.domain.vo.TimeDuration;
import com.arash.hexagonal.domain.vo.VisitDate;
import com.arash.hexagonal.infrastructure.output.doctor.DoctorEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;


@Repository
public interface DoctorRepository extends JpaRepository<DoctorEntity, Long> {

    @EntityGraph(attributePaths = {"appointments"})
    Optional<DoctorEntity> findDetailById(Long id);

    Optional<DoctorEntityRoot> findRootById(Long id);

    @Query(value = "SELECT ap.id.visitDate AS visitDate,ap.id.startTime AS startTime,ap.id.endTime AS endTime," +
            "p.name AS name,p.phoneNumber AS phoneNumber , ap.version AS version FROM DoctorEntity d " +
            " INNER JOIN AppointmentEntity ap ON d.id = ap.doctor.id" +
            " LEFT JOIN PatientEntity p ON ap.patient.id=p.id" +
            " WHERE d.id=?1")
    List<DoctorAppointment> findDoctorAppointmentById(Long id);

    interface DoctorEntityRoot {
        static Doctor toDomain(DoctorEntityRoot doctorRootEntity) {
            return Doctor.of(doctorRootEntity.getId(),
                    doctorRootEntity.getMedicalNo(),
                    doctorRootEntity.getFullName());
        }

        Long getId();

        String getFullName();

        Long getMedicalNo();
    }

    /*
        interface DoctorOpenTime {
            LocalDate getVisitDate();

            LocalTime getStartTime();

            LocalTime getEndTime();

            static OpenTime toDomain(DoctorOpenTime doctorOpenTime) {
                return new OpenTime(new VisitDate(doctorOpenTime.getVisitDate()),
                        new TimeDuration(doctorOpenTime.getStartTime(), doctorOpenTime.getEndTime()));
            }
        }
    */

    interface DoctorAppointment {
        static Appointment toDomain(DoctorAppointment doctorAppointment) {
            return Appointment.of(null,
                    doctorAppointment.getPhoneNumber() != null &&
                            doctorAppointment.getName() != null ? Patient.of(null, doctorAppointment.getName()
                            , doctorAppointment.getPhoneNumber()) : null,
                    new OpenTime(new VisitDate(doctorAppointment.getVisitDate()), new TimeDuration(doctorAppointment.getStartTime(), doctorAppointment.getEndTime())),
                    doctorAppointment.getVersion());
        }

        LocalDate getVisitDate();

        LocalTime getStartTime();

        LocalTime getEndTime();

        String getPhoneNumber();

        String getName();

        Integer getVersion();
    }
}
