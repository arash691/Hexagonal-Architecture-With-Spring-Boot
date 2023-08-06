package com.arash.hexagonal.infrastructure.output.adaptor;

import com.arash.hexagonal.application.ports.output.DoctorPersistencePort;
import com.arash.hexagonal.domain.entity.Doctor;
import com.arash.hexagonal.domain.exception.DomainNotFoundException;
import com.arash.hexagonal.domain.exception.RemoveDomainException;
import com.arash.hexagonal.domain.vo.Appointment;
import com.arash.hexagonal.domain.vo.ID;
import com.arash.hexagonal.domain.vo.OpenTime;
import com.arash.hexagonal.domain.vo.VisitDate;
import com.arash.hexagonal.infrastructure.output.AppointmentRepository;
import com.arash.hexagonal.infrastructure.output.DoctorRepository;
import com.arash.hexagonal.infrastructure.output.appointment.AppointmentPK;
import com.arash.hexagonal.infrastructure.output.doctor.DoctorEntity;
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
public class DoctorPersistenceAdaptor implements DoctorPersistencePort {
    private final DoctorRepository doctorRepository;
    private final AppointmentRepository appointmentRepository;

    @Autowired
    public DoctorPersistenceAdaptor(DoctorRepository doctorRepository, AppointmentRepository appointmentRepository) {
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
        this.appointmentRepository.findById(new AppointmentPK(id.getId(), openTime.visitDate().value()
                        , openTime.timeDuration().begin()
                        , openTime.timeDuration().end()))
                .ifPresentOrElse(appointmentEntity -> {
                            if (appointmentEntity.getPatient() == null) {
                                this.appointmentRepository.deleteOpenTime(appointmentEntity.getId(), appointmentEntity.getVersion());
                            } else {
                                throw new RemoveDomainException("taken time could not removed");
                            }
                        },
                        () -> {
                            throw new DomainNotFoundException("openTimes not found");
                        });
    }


}
