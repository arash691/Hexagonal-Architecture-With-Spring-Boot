package com.blubank.doctorappointment.infrastructure.output;

import com.blubank.doctorappointment.infrastructure.output.appointment.AppointmentEntity;
import com.blubank.doctorappointment.infrastructure.output.appointment.AppointmentPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<AppointmentEntity, AppointmentPK> {
}
