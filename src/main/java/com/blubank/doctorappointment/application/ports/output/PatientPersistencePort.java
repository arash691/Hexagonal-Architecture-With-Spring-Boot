package com.blubank.doctorappointment.application.ports.output;

import com.blubank.doctorappointment.domain.vo.OpenTime;
import com.blubank.doctorappointment.domain.entity.Patient;
import com.blubank.doctorappointment.domain.vo.Appointment;
import com.blubank.doctorappointment.domain.vo.ID;
import com.blubank.doctorappointment.domain.vo.VisitDate;

import java.util.List;

public interface PatientPersistencePort {
    List<OpenTime> findAllByVisitDate(VisitDate visitDate);
    Patient findDetailedById(ID id);
    List<Appointment> findAppointments(ID id);
}
