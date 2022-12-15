package com.arash.hexagonal.application.ports.output;

import com.arash.hexagonal.domain.entity.Doctor;
import com.arash.hexagonal.domain.entity.Patient;
import com.arash.hexagonal.domain.vo.*;

import java.util.List;

public interface PatientPersistencePort {
    List<OpenTime> findAllByVisitDate(VisitDate visitDate);

    Patient createAppointment(Doctor doctor, Patient patient, OpenTime openTime);

    Patient findDetailedById(ID id);

    List<Appointment> findAppointments(PhoneNumber phoneNumber);
}
