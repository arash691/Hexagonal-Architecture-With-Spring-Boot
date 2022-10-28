package com.blubank.doctorappointment.infrastructure.output;

import com.blubank.doctorappointment.domain.vo.OpenTime;
import com.blubank.doctorappointment.infrastructure.output.appointment.AppointmentEntity;
import com.blubank.doctorappointment.infrastructure.output.appointment.AppointmentPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<AppointmentEntity, AppointmentPK> {

    @Modifying
    @Query(value = "DELETE FROM AppointmentEntity ap WHERE ap.id=:apKey AND ap.patient.id IS NULL AND ap.version=:version")
    void deleteOpenTime(@Param("apKey") AppointmentPK appointmentPK, @Param("version") Integer version);

    @Query(value = "SELECT ap.id.visitDate AS visitDate,ap.id.startTime AS startTime " +
            ", ap.id.endTime AS endTime FROM AppointmentEntity ap WHERE ap.id.visitDate=:visitDate AND ap.patient.id IS NULL")
    List<OpenAppointment> findOpenTimeByVisitDate(@Param("visitDate") LocalDate visitDate);

    interface OpenAppointment {
        static OpenTime toDomain(OpenAppointment openAppointment) {
            return OpenTime.of(openAppointment.getVisitDate(),
                    openAppointment.getStartTime(),
                    openAppointment.getEndTime());
        }

        LocalDate getVisitDate();

        LocalTime getStartTime();

        LocalTime getEndTime();
    }
}
