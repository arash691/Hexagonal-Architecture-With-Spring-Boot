package com.arash.hexagonal.application.ports.output;

import com.arash.hexagonal.domain.entity.Doctor;
import com.arash.hexagonal.domain.vo.Appointment;
import com.arash.hexagonal.domain.vo.Id;
import com.arash.hexagonal.domain.vo.OpenTime;
import com.arash.hexagonal.domain.vo.VisitDate;

import java.util.List;

public interface DoctorPersistencePort {

    Doctor createOrUpdate(Doctor doctor);

    Doctor findDetailedById(Id id);

    Doctor findRootById(Id id);

    List<Appointment> findAllAppointments(Id id);

    List<Appointment> findAllTakenTimes(Id id);

    List<OpenTime> findAllAppointmentsByVisitDate(VisitDate visitDate);

    void removeOpenTimes(Id id, OpenTime openTime);
}
