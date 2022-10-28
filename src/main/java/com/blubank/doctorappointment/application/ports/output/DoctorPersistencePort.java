package com.blubank.doctorappointment.application.ports.output;

import com.blubank.doctorappointment.domain.entity.Doctor;
import com.blubank.doctorappointment.domain.vo.Appointment;
import com.blubank.doctorappointment.domain.vo.ID;
import com.blubank.doctorappointment.domain.vo.OpenTime;
import com.blubank.doctorappointment.domain.vo.VisitDate;

import java.util.List;

public interface DoctorPersistencePort {

    Doctor createOrUpdate(Doctor doctor);

    Doctor findDetailedById(ID id);

    Doctor findRootById(ID id);

    List<Appointment> findAllAppointments(ID id);

    List<Appointment> findAllTakenTimes(ID id);

    List<OpenTime> findAllAppointmentsByVisitDate(VisitDate visitDate);

    void removeOpenTimes(ID id, OpenTime openTime);
}
