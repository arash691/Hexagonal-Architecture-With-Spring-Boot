package com.blubank.doctorappointment.application.ports.input;

import com.blubank.doctorappointment.application.ports.output.PatientPersistencePort;
import com.blubank.doctorappointment.application.dto.PatientAppointment;
import com.blubank.doctorappointment.domain.entity.Doctor;
import com.blubank.doctorappointment.domain.entity.Patient;
import com.blubank.doctorappointment.domain.vo.OpenTime;
import com.blubank.doctorappointment.domain.vo.*;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<PatientAppointment> findAllAppointmentsByPhoneNumber(PhoneNumber phoneNumber) {
        return patientPersistencePort.findAppointments(phoneNumber)
                .stream()
                .map(PatientAppointment::from)
                .collect(Collectors.toList());

    }
}
