package com.arash.hexagonal.application.ports.input;


import com.arash.hexagonal.domain.entity.Doctor;
import com.arash.hexagonal.domain.vo.Appointment;
import com.arash.hexagonal.domain.vo.ID;
import com.arash.hexagonal.domain.vo.OpenTime;

import java.util.List;

public interface DoctorServicePort {

    Doctor create(Doctor doctor);

    Doctor createOpenTime(ID id, OpenTime openTime);

    List<Appointment> findAllDoctorOpenAndTakenTimes(ID id);

    void removeOpenTime(ID id, OpenTime openTime);
}
