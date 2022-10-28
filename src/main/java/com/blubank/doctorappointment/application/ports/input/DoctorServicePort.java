package com.blubank.doctorappointment.application.ports.input;


import com.blubank.doctorappointment.domain.entity.Doctor;
import com.blubank.doctorappointment.domain.vo.Appointment;
import com.blubank.doctorappointment.domain.vo.ID;
import com.blubank.doctorappointment.domain.vo.OpenTime;

import java.util.List;

public interface DoctorServicePort {

    Doctor create(Doctor doctor);

    Doctor createOpenTime(ID id, OpenTime openTime);

    List<Appointment> findAllDoctorOpenAndTakenTimes(ID id);

    void removeOpenTime(ID id, OpenTime openTime);
}
