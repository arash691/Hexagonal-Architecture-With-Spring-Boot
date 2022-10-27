package com.blubank.doctorappointment.application.ports.input;


import com.blubank.doctorappointment.application.query.DoctorAppointment;
import com.blubank.doctorappointment.domain.entity.Doctor;
import com.blubank.doctorappointment.domain.vo.OpenTime;
import com.blubank.doctorappointment.domain.vo.*;
import java.util.List;

public interface DoctorServicePort {

    Doctor create(Doctor doctor);

    Doctor createOpenTime(ID id, OpenTime openTime);

    List<DoctorAppointment> findAllDoctorOpenAndTakenTimes(ID id);

    void removeOpenTime(ID id, OpenTime openTime);
}
