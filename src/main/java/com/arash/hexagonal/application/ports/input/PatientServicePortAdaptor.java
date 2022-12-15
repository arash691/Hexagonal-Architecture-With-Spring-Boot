package com.arash.hexagonal.application.ports.input;

import com.arash.hexagonal.application.ports.output.PatientPersistencePort;
import com.arash.hexagonal.domain.vo.OpenTime;
import com.arash.hexagonal.domain.entity.Doctor;
import com.arash.hexagonal.domain.entity.Patient;
import com.arash.hexagonal.domain.vo.Appointment;
import com.arash.hexagonal.domain.vo.PhoneNumber;
import com.arash.hexagonal.domain.vo.VisitDate;

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
    public Patient createAppointment(Doctor doctor, Patient patient, OpenTime openTime) {
        return patientPersistencePort.createAppointment(doctor, patient, openTime);
    }

    @Override
    public List<Appointment> findAllAppointmentsByPhoneNumber(PhoneNumber phoneNumber) {
        return patientPersistencePort.findAppointments(phoneNumber);

    }
}
