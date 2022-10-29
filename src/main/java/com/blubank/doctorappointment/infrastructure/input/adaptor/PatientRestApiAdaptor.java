package com.blubank.doctorappointment.infrastructure.input.adaptor;

import com.blubank.doctorappointment.application.ports.input.PatientServicePort;
import com.blubank.doctorappointment.domain.entity.Doctor;
import com.blubank.doctorappointment.domain.entity.Patient;
import com.blubank.doctorappointment.domain.exception.EmptyFullNameException;
import com.blubank.doctorappointment.domain.exception.EmptyPhoneNumberException;
import com.blubank.doctorappointment.domain.vo.OpenTime;
import com.blubank.doctorappointment.domain.vo.PhoneNumber;
import com.blubank.doctorappointment.domain.vo.VisitDate;
import com.blubank.doctorappointment.infrastructure.input.request.PatientTakeAppointmentRequest;
import com.blubank.doctorappointment.infrastructure.input.response.PatientAppointmentResponse;
import com.blubank.doctorappointment.infrastructure.input.response.ResponseFactory;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(path = "/appointments")
    public ResponseEntity<?> takeAppointment(@RequestBody PatientTakeAppointmentRequest patientTakeAppointmentRequest) {
        try {
            return ResponseFactory.ok(patientServicePort.createAppointment(Doctor.of(patientTakeAppointmentRequest.getDoctorId()),
                    Patient.of(patientTakeAppointmentRequest.getName(), patientTakeAppointmentRequest.getPhoneNumber()),
                    OpenTime.of(patientTakeAppointmentRequest.getVisitDateInfo().getVisitDate(),
                            patientTakeAppointmentRequest.getVisitDateInfo().getStartTime(),
                            patientTakeAppointmentRequest.getVisitDateInfo().getEndTime())),
                    patient -> PatientAppointmentResponse.from(patient.getAppointments()));
        } catch (EmptyFullNameException | EmptyPhoneNumberException e) {
            return ResponseFactory.badRequest(e.getMessage());
        }
    }

    @GetMapping(path = "/appointments")
    public ResponseEntity<?> findAllAppointments(@RequestParam(name = "phone-number") String phoneNumber) {
        return ResponseFactory.ok(patientServicePort.findAllAppointmentsByPhoneNumber(PhoneNumber.of(phoneNumber)),
                PatientAppointmentResponse::from);
    }
}
