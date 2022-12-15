package com.arash.hexagonal.application.ports.output;

import com.arash.hexagonal.domain.entity.Doctor;
import com.arash.hexagonal.domain.vo.Appointment;
import com.arash.hexagonal.domain.vo.ID;
import com.arash.hexagonal.domain.vo.OpenTime;
import com.arash.hexagonal.domain.vo.VisitDate;

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
