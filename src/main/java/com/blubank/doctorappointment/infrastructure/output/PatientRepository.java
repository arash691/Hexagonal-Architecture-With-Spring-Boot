package com.blubank.doctorappointment.infrastructure.output;

import com.blubank.doctorappointment.infrastructure.output.patient.PatientEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<PatientEntity,Long> {
    @EntityGraph(attributePaths = {"appointments"})
    Optional<PatientEntity> findDetailById(Long id);
}
