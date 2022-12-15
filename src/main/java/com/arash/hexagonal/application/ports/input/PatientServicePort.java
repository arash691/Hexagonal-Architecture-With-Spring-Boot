package com.arash.hexagonal.application.ports.input;

import com.arash.hexagonal.domain.vo.OpenTime;
import com.arash.hexagonal.domain.entity.Doctor;
import com.arash.hexagonal.domain.entity.Patient;
import com.arash.hexagonal.domain.vo.Appointment;
import com.arash.hexagonal.domain.vo.PhoneNumber;
import com.arash.hexagonal.domain.vo.VisitDate;

import java.util.List;

public interface PatientServicePort {
    List<OpenTime> findAllOpenTimesByVisitDate(VisitDate visitDate);

    Patient createAppointment(Doctor doctor, Patient patient, OpenTime openTime);

    List<Appointment> findAllAppointmentsByPhoneNumber(PhoneNumber phoneNumber);

}
