package com.blubank.doctorappointment.application.ports.input;

import com.blubank.doctorappointment.domain.entity.Doctor;
import com.blubank.doctorappointment.domain.entity.Patient;
import com.blubank.doctorappointment.domain.vo.OpenTime;
import com.blubank.doctorappointment.domain.vo.*;

import java.util.List;

public interface PatientServicePort {
    List<OpenTime> findAllOpenTimesByVisitDate(VisitDate visitDate);

    Patient createAppointment(Doctor doctor, Patient patient, OpenTime openTime);

    List<Appointment> findAllAppointmentsByPhoneNumber(PhoneNumber phoneNumber);

}
