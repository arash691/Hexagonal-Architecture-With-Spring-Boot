package com.blubank.doctorappointment.infrastructure.output;

import com.blubank.doctorappointment.domain.entity.Doctor;
import com.blubank.doctorappointment.domain.vo.OpenTime;
import com.blubank.doctorappointment.domain.entity.Patient;
import com.blubank.doctorappointment.domain.vo.Appointment;
import com.blubank.doctorappointment.infrastructure.output.doctor.DoctorEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;


@Repository
public interface DoctorRepository extends JpaRepository<DoctorEntity,Long> {

    @EntityGraph(attributePaths = {"openTimes", "appointments"})
    Optional<DoctorEntity> findDetailById(Long id);

    Optional<DoctorEntityRoot> findRootById(Long id);

    @Query(value = "SELECT o.visitDate AS visitDate,o.startTime AS startTime , o.endTime AS endTime" +
            " FROM DoctorEntity d INNER JOIN d.openTimes o" +
            " WHERE d.id = ?1 AND o.id NOT IN(SELECT ap.openTime FROM AppointmentEntity ap WHERE d.id=?1)")
    List<DoctorOpenTime> findDoctorOpenTimesById(Long id);

    @Query(value = "SELECT o.visitDate AS visitDate,o.startTime AS startTime,o.endTime AS endTime," +
            "p.name AS name,p.phoneNumber AS phoneNumber FROM DoctorEntity d " +
            " INNER JOIN AppointmentEntity ap ON d.id = ap.doctor.id" +
            " INNER JOIN OpenTimeEntity o ON ap.openTime.id=o.id" +
            " INNER JOIN PatientEntity p ON ap.patient.id=p.id" +
            " WHERE d.id=?1")
    List<DoctorTakenTime> findDoctorTakenTimesById(Long id);

    interface DoctorEntityRoot {
        Long getId();

        String getFullName();

        Long getMedicalNo();

        static Doctor toDomain(DoctorEntityRoot doctorRootEntity) {
            return Doctor.of(doctorRootEntity.getId(),
                    doctorRootEntity.getMedicalNo(),
                    doctorRootEntity.getFullName());
        }
    }

    interface DoctorOpenTime {
        LocalDate getVisitDate();

        LocalTime getStartTime();

        LocalTime getEndTime();

        static OpenTime toDomain(DoctorOpenTime doctorOpenTime) {
            return OpenTime.of(doctorOpenTime.getVisitDate(),
                    doctorOpenTime.getStartTime(),
                    doctorOpenTime.getEndTime());
        }
    }

    interface DoctorTakenTime {
        LocalDate getVisitDate();

        LocalTime getStartTime();

        LocalTime getEndTime();

        String getPhoneNumber();

        String getName();

        static Appointment toDomain(DoctorTakenTime doctorTakenTime) {
            return Appointment.of(null,
                    Patient.of(null, doctorTakenTime.getName()
                            , doctorTakenTime.getPhoneNumber()),
                    OpenTime.of(doctorTakenTime.getVisitDate(),
                            doctorTakenTime.getStartTime(),
                            doctorTakenTime.getEndTime()));
        }
    }
}
