package com.blubank.doctorappointment.application.ports.input;

import com.blubank.doctorappointment.application.ports.output.DoctorPersistencePort;
import com.blubank.doctorappointment.domain.entity.Doctor;
import com.blubank.doctorappointment.domain.vo.Appointment;
import com.blubank.doctorappointment.domain.vo.ID;
import com.blubank.doctorappointment.domain.vo.OpenTime;

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
    public Doctor createOpenTime(ID id, OpenTime openTime) {
        Doctor doctor = this.doctorPersistencePort.findDetailedById(id);
        doctor.addOpenTimes(openTime);
        return doctorPersistencePort.createOrUpdate(doctor);
    }

    @Override
    public List<Appointment> findAllDoctorOpenAndTakenTimes(ID id) {
        return this.doctorPersistencePort.findAllAppointments(id);
    }

    @Override
    public void removeOpenTime(ID id, OpenTime openTimes) {
        this.doctorPersistencePort.removeOpenTimes(id, openTimes);
    }
}
