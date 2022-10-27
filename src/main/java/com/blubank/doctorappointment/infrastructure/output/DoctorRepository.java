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
        Long getId();

        String getFullName();

        Long getMedicalNo();

        static Doctor toDomain(DoctorEntityRoot doctorRootEntity) {
            return Doctor.of(doctorRootEntity.getId(),
                    doctorRootEntity.getMedicalNo(),
                    doctorRootEntity.getFullName());
        }
    }

/*
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
*/

    interface DoctorAppointment {
        LocalDate getVisitDate();

        LocalTime getStartTime();

        LocalTime getEndTime();

        String getPhoneNumber();

        String getName();

        Integer getVersion();

        static Appointment toDomain(DoctorAppointment doctorAppointment) {
            return Appointment.of(null,
                    doctorAppointment.getPhoneNumber() != null &&
                            doctorAppointment.getName() != null ? Patient.of(null, doctorAppointment.getName()
                            , doctorAppointment.getPhoneNumber()) : null,
                    OpenTime.of(doctorAppointment.getVisitDate(),
                            doctorAppointment.getStartTime(),
                            doctorAppointment.getEndTime()),doctorAppointment.getVersion());
        }
    }
}
