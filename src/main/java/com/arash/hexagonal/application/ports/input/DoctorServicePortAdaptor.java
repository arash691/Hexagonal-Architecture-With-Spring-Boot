package com.arash.hexagonal.application.ports.input;

import com.arash.hexagonal.application.ports.output.DoctorPersistencePort;
import com.arash.hexagonal.domain.entity.Doctor;
import com.arash.hexagonal.domain.vo.Appointment;
import com.arash.hexagonal.domain.vo.Id;
import com.arash.hexagonal.domain.vo.OpenTime;

import java.util.List;

/**
 * @author a.ariani
 */

public class DoctorServicePortAdaptor implements DoctorServicePort {
    private final DoctorPersistencePort doctorPersistencePort;

    public DoctorServicePortAdaptor(DoctorPersistencePort doctorPersistencePort) {
        this.doctorPersistencePort = doctorPersistencePort;
    }


    @Override
    public Doctor create(Doctor doctor) {
        return doctorPersistencePort.createOrUpdate(doctor);
    }


    @Override
    public Doctor createOpenTime(Id id, OpenTime openTime) {
        Doctor doctor = this.doctorPersistencePort.findDetailedById(id);
        doctor.addOpenTime(openTime);
        return doctorPersistencePort.createOrUpdate(doctor);
    }

    @Override
    public List<Appointment> findAllDoctorOpenAndTakenTimes(Id id) {
        return this.doctorPersistencePort.findAllAppointments(id);
    }

    @Override
    public void removeOpenTime(Id id, OpenTime openTimes) {
        this.doctorPersistencePort.removeOpenTimes(id, openTimes);
    }
}
