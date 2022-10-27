package com.blubank.doctorappointment.application.ports.input;

import com.blubank.doctorappointment.domain.vo.OpenTime;
import com.blubank.doctorappointment.domain.vo.*;

import java.util.List;

public interface PatientServicePort {
    List<OpenTime> findAllOpenTimesByVisitDate(VisitDate visitDate);

    Appointment createAppointment(ID doctorId, PhoneNumber phoneNumber, FullName fullName, OpenTime openTime);

    List<Appointment> findAllAppointmentsByPhoneNumber(PhoneNumber phoneNumber);

}
