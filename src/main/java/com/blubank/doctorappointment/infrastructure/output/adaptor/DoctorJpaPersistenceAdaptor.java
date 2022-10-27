package com.blubank.doctorappointment.infrastructure.output.adaptor;

import com.blubank.doctorappointment.application.ports.output.DoctorPersistencePort;
import com.blubank.doctorappointment.domain.entity.Doctor;
import com.blubank.doctorappointment.domain.vo.OpenTime;
import com.blubank.doctorappointment.domain.exception.DomainNotFoundException;
import com.blubank.doctorappointment.domain.vo.Appointment;
import com.blubank.doctorappointment.domain.vo.VisitDate;
import com.blubank.doctorappointment.infrastructure.output.OpenTimeRepository;
import com.blubank.doctorappointment.infrastructure.output.doctor.DoctorEntity;
import com.blubank.doctorappointment.domain.vo.ID;
import com.blubank.doctorappointment.infrastructure.output.DoctorRepository;
import com.blubank.doctorappointment.infrastructure.output.opentime.OpenTimeEntity;
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
    private final OpenTimeRepository openTimeRepository;

    @Autowired
    public DoctorJpaPersistenceAdaptor(DoctorRepository doctorRepository, OpenTimeRepository openTimeRepository) {
        this.doctorRepository = doctorRepository;
        this.openTimeRepository = openTimeRepository;
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
    public List<OpenTime> findAllOpenTimes(ID id) {
        return this.doctorRepository.findDoctorOpenTimesById(id.getId()).stream()
                .map(DoctorRepository.DoctorOpenTime::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Appointment> findAllTakenTimes(ID id) {
        return this.doctorRepository.findDoctorTakenTimesById(id.getId()).stream()
                .map(DoctorRepository.DoctorTakenTime::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<OpenTime> findAllByVisitDate(VisitDate visitDate) {
        return this.openTimeRepository.findAllByVisitDate(visitDate.getVisitDate())
                .stream()
                .map(OpenTimeEntity::toDomain)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void removeOpenTimes(ID id, List<OpenTime> openTimes) {
        this.doctorRepository.findDetailById(id.getId()).ifPresent(doctorEntity -> {
            if (!doctorEntity.getOpenTimes().isEmpty()) {
                doctorEntity.getOpenTimes().removeAll(openTimes.stream()
                        .map(openTime -> OpenTimeEntity.from(doctorEntity, openTime))
                        .collect(Collectors.toList()));
                this.doctorRepository.save(doctorEntity);
            } else {
                throw new DomainNotFoundException("openTimes not found");
            }
        });
    }


}
