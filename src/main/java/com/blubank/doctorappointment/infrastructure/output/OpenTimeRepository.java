package com.blubank.doctorappointment.infrastructure.output;

import com.blubank.doctorappointment.infrastructure.output.opentime.OpenTimeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OpenTimeRepository extends JpaRepository<OpenTimeEntity,Long> {
    List<OpenTimeEntity> findAllByVisitDate(LocalDate visitDate);
}
