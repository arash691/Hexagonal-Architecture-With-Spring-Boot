package com.blubank.doctorappointment.application.ports.input;

import com.blubank.doctorappointment.application.ports.output.PatientPersistencePort;
import com.blubank.doctorappointment.domain.vo.OpenTime;
import com.blubank.doctorappointment.domain.vo.*;

import java.util.List;

/**
 * @author a.ariani
 */

public class PatientServicePortAdaptor implements PatientServicePort {
    private final PatientPersistencePort patientPersistencePort;

    public PatientServicePortAdaptor(PatientPersistencePort patientPersistencePort) {
        this.patientPersistencePort = patientPersistencePort;
    }

    @Override
    public List<OpenTime> findAllOpenTimesByVisitDate(VisitDate visitDate) {
        return patientPersistencePort.findAllByVisitDate(visitDate);
    }

    @Override
    public Appointment createAppointment(ID doctorId, PhoneNumber phoneNumber, FullName fullName, OpenTime openTime) {
        return null;
    }

    @Override
    public List<Appointment> findAllAppointmentsByPhoneNumber(PhoneNumber phoneNumber) {
        return List.of();

    }
}
