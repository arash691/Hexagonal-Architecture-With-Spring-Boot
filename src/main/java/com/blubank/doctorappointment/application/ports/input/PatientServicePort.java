package com.blubank.doctorappointment.application.ports.input;

import com.blubank.doctorappointment.application.dto.PatientAppointment;
import com.blubank.doctorappointment.domain.entity.Doctor;
import com.blubank.doctorappointment.domain.entity.Patient;
import com.blubank.doctorappointment.domain.vo.OpenTime;
import com.blubank.doctorappointment.domain.vo.PhoneNumber;
import com.blubank.doctorappointment.domain.vo.VisitDate;

import java.util.List;

public interface PatientServicePort {
    List<OpenTime> findAllOpenTimesByVisitDate(VisitDate visitDate);

    Patient createAppointment(Doctor doctor, Patient patient, OpenTime openTime);

    List<PatientAppointment> findAllAppointmentsByPhoneNumber(PhoneNumber phoneNumber);

}
