package com.blubank.doctorappointment.infrastructure.input.adaptor;

import com.blubank.doctorappointment.application.ports.input.PatientServicePort;
import com.blubank.doctorappointment.domain.vo.VisitDate;
import com.blubank.doctorappointment.infrastructure.input.response.ResponseFactory;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Table;
import java.time.LocalDate;

/**
 * @author a.ariani
 */
@Tag(name = "Patient")
@RestController
@RequestMapping(path = "/api/v1/patients")
public class PatientRestApiAdaptor {

    private final PatientServicePort patientServicePort;

    @Autowired
    public PatientRestApiAdaptor(PatientServicePort patientServicePort) {
        this.patientServicePort = patientServicePort;
    }

    @GetMapping(path = "/open-times")
    public ResponseEntity<?> findAllOpenTimes(@RequestParam(name = "visitDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate visitDate) {
        return ResponseFactory.ok(patientServicePort.findAllOpenTimesByVisitDate(VisitDate.of(visitDate)));
    }
}
