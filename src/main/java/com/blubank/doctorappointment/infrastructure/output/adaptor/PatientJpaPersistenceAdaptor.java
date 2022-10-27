package com.blubank.doctorappointment.infrastructure.output.adaptor;

import com.blubank.doctorappointment.application.ports.output.PatientPersistencePort;
import com.blubank.doctorappointment.domain.vo.OpenTime;
import com.blubank.doctorappointment.domain.entity.Patient;
import com.blubank.doctorappointment.domain.exception.DomainNotFoundException;
import com.blubank.doctorappointment.domain.vo.Appointment;
import com.blubank.doctorappointment.domain.vo.ID;
import com.blubank.doctorappointment.domain.vo.VisitDate;
import com.blubank.doctorappointment.infrastructure.output.OpenTimeRepository;
import com.blubank.doctorappointment.infrastructure.output.PatientRepository;
import com.blubank.doctorappointment.infrastructure.output.opentime.OpenTimeEntity;
import com.blubank.doctorappointment.infrastructure.output.patient.PatientEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author a.ariani
 */

@Component
public class PatientJpaPersistenceAdaptor implements PatientPersistencePort {
    private final PatientRepository patientRepository;
    private final OpenTimeRepository openTimeRepository;

    @Autowired
    public PatientJpaPersistenceAdaptor(PatientRepository patientRepository, OpenTimeRepository openTimeRepository) {
        this.patientRepository = patientRepository;
        this.openTimeRepository = openTimeRepository;
    }


    @Override
    public List<OpenTime> findAllByVisitDate(VisitDate visitDate) {
        return openTimeRepository.findAllByVisitDate(visitDate.getVisitDate())
                .stream().map(OpenTimeEntity::toDomain).collect(Collectors.toList());
    }

    @Override
    public Patient findDetailedById(ID id) {
        return patientRepository.findDetailById(id.getId()).
                map(PatientEntity::toDomain)
                .orElseThrow(() -> {
                    throw new DomainNotFoundException(MessageFormat.format("patient with id '{'{0}'}' notfound", id.getId()));
                });
    }

    @Override
    public List<Appointment> findAppointments(ID id) {
        return null;
    }

}
