package com.blubank.doctorappointment.application.ports.input;

import com.blubank.doctorappointment.application.ports.output.DoctorPersistencePort;
import com.blubank.doctorappointment.application.dto.DoctorAppointment;
import com.blubank.doctorappointment.domain.entity.Doctor;
import com.blubank.doctorappointment.domain.vo.ID;
import com.blubank.doctorappointment.domain.vo.OpenTime;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<DoctorAppointment> findAllDoctorOpenAndTakenTimes(ID id) {
        return this.doctorPersistencePort.findAllAppointments(id)
                .stream()
                .map(DoctorAppointment::from)
                .collect(Collectors.toList());
    }

    @Override
    public void removeOpenTime(ID id, OpenTime openTimes) {
        this.doctorPersistencePort.removeOpenTimes(id, openTimes);
    }
}
