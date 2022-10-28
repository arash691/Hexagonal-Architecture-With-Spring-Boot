package com.blubank.doctorappointment.application.ports.output;

import com.blubank.doctorappointment.domain.entity.Doctor;
import com.blubank.doctorappointment.domain.entity.Patient;
import com.blubank.doctorappointment.domain.vo.*;

import java.util.List;

public interface PatientPersistencePort {
    List<OpenTime> findAllByVisitDate(VisitDate visitDate);

    Patient createAppointment(Doctor doctor, Patient patient, OpenTime openTime);

    Patient findDetailedById(ID id);

    List<Appointment> findAppointments(PhoneNumber phoneNumber);
}
