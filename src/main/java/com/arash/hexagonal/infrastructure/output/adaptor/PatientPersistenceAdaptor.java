package com.arash.hexagonal.infrastructure.output.adaptor;

import com.arash.hexagonal.application.ports.output.PatientPersistencePort;
import com.arash.hexagonal.domain.vo.*;
import com.arash.hexagonal.infrastructure.output.appointment.AppointmentEntity;
import com.arash.hexagonal.domain.entity.Doctor;
import com.arash.hexagonal.domain.entity.Patient;
import com.arash.hexagonal.domain.exception.DomainConflictException;
import com.arash.hexagonal.domain.exception.DomainNotFoundException;
import com.arash.hexagonal.infrastructure.output.AppointmentRepository;
import com.arash.hexagonal.infrastructure.output.PatientRepository;
import com.arash.hexagonal.infrastructure.output.appointment.AppointmentPK;
import com.arash.hexagonal.infrastructure.output.patient.PatientEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author a.ariani
 */
@Transactional(readOnly = true)
@Component
public class PatientPersistenceAdaptor implements PatientPersistencePort {
    private final PatientRepository patientRepository;
    private final AppointmentRepository appointmentRepository;


    @Autowired
    public PatientPersistenceAdaptor(PatientRepository patientRepository, AppointmentRepository appointmentRepository) {
        this.patientRepository = patientRepository;
        this.appointmentRepository = appointmentRepository;
    }


    @Override
    public List<OpenTime> findAllByVisitDate(VisitDate visitDate) {
        return this.appointmentRepository.findOpenTimeByVisitDate(visitDate.getVisitDate())
                .stream().map(AppointmentRepository.OpenAppointment::toDomain)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public Patient createAppointment(Doctor doctor, Patient patient, OpenTime openTime) {
        AppointmentEntity appointmentEntity = this.appointmentRepository.findById(new AppointmentPK(doctor.getId().getId(), openTime.getVisitDate().getVisitDate(),
                openTime.getTimeDuration().getStart(),
                openTime.getTimeDuration().getEnd()))
                .orElseThrow(() -> {
                    throw new DomainNotFoundException("time not found");
                });
        if (appointmentEntity.getPatient() == null) {
            PatientEntity entity = PatientEntity.from(patient);
            entity.addAppointment(appointmentEntity);
            PatientEntity save = this.patientRepository.save(entity);
            return save.toDomain();
        }
        throw new DomainConflictException("time is taken");
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
    public List<Appointment> findAppointments(PhoneNumber phoneNumber) {
        return patientRepository.findAppointmentByPhoneNumber(phoneNumber.getPhoneNumber())
                .stream()
                .map(PatientRepository.PatientAppointment::toDomain)
                .collect(Collectors.toList());
    }

}
