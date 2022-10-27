package com.blubank.doctorappointment.infrastructure.output.adaptor;

import com.blubank.doctorappointment.application.ports.output.DoctorPersistencePort;
import com.blubank.doctorappointment.domain.entity.Doctor;
import com.blubank.doctorappointment.domain.exception.RemoveDomainException;
import com.blubank.doctorappointment.domain.vo.OpenTime;
import com.blubank.doctorappointment.domain.exception.DomainNotFoundException;
import com.blubank.doctorappointment.domain.vo.Appointment;
import com.blubank.doctorappointment.domain.vo.VisitDate;
import com.blubank.doctorappointment.infrastructure.output.AppointmentRepository;
import com.blubank.doctorappointment.infrastructure.output.appointment.AppointmentPK;
import com.blubank.doctorappointment.infrastructure.output.doctor.DoctorEntity;
import com.blubank.doctorappointment.domain.vo.ID;
import com.blubank.doctorappointment.infrastructure.output.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author a.ariani
 */
@Transactional(readOnly = true)
@Service
public class DoctorJpaPersistenceAdaptor implements DoctorPersistencePort {
    private final DoctorRepository doctorRepository;
    private final AppointmentRepository appointmentRepository;

    @Autowired
    public DoctorJpaPersistenceAdaptor(DoctorRepository doctorRepository, AppointmentRepository appointmentRepository) {
        this.doctorRepository = doctorRepository;
        this.appointmentRepository = appointmentRepository;
    }

    @Transactional
    @Override
    public Doctor createOrUpdate(Doctor doctor) {
        DoctorEntity doctorEntity = DoctorEntity.from(doctor);
        DoctorEntity save = this.doctorRepository.save(doctorEntity);
        return save.toDomain();
    }


    @Override
    public Doctor findDetailedById(ID id) {
        return this.doctorRepository.findDetailById(id.getId())
                .map(DoctorEntity::toDomain)
                .orElseThrow(() -> {
                    throw new DomainNotFoundException(MessageFormat.format("doctor with id '{'{0}'}' notfound", id.getId()));
                });
    }

    @Override
    public Doctor findRootById(ID id) {
        return this.doctorRepository.findRootById(id.getId())
                .map(DoctorRepository.DoctorEntityRoot::toDomain)
                .orElseThrow(() -> {
                    throw new DomainNotFoundException(MessageFormat.format("doctor with id '{'{0}'}' notfound", id.getId()));
                });
    }

    @Override
    public List<Appointment> findAllAppointments(ID id) {
        return this.doctorRepository.findDoctorAppointmentById(id.getId()).stream()
                .map(DoctorRepository.DoctorAppointment::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Appointment> findAllTakenTimes(ID id) {
        return List.of();
    }

    @Override
    public List<OpenTime> findAllAppointmentsByVisitDate(VisitDate visitDate) {
        return List.of();
    }

    @Transactional
    @Override
    public void removeOpenTimes(ID id, OpenTime openTime) {
        this.appointmentRepository.findById(new AppointmentPK(id.getId(), openTime.getVisitDate().getVisitDate()
                , openTime.getTimeDuration().getStart()
                , openTime.getTimeDuration().getEnd()))
                .ifPresentOrElse(appointmentEntity -> {
                            if (appointmentEntity.getPatient() == null) {
                                this.appointmentRepository.deleteOpenTime(appointmentEntity.getId(),appointmentEntity.getVersion());
                            } else {
                                throw new RemoveDomainException("taken time could not removed");
                            }
                        },
                        () -> {
                            throw new DomainNotFoundException("openTimes not found");
                        });
    }


}
