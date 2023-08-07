package com.arash.hexagonal.application.ports.input;


import com.arash.hexagonal.domain.entity.Doctor;
import com.arash.hexagonal.domain.vo.Appointment;
import com.arash.hexagonal.domain.vo.Id;
import com.arash.hexagonal.domain.vo.OpenTime;

import java.util.List;

public interface DoctorServicePort {

    Doctor create(Doctor doctor);

    Doctor createOpenTime(Id id, OpenTime openTime);

    List<Appointment> findAllDoctorOpenAndTakenTimes(Id id);

    void removeOpenTime(Id id, OpenTime openTime);
}
