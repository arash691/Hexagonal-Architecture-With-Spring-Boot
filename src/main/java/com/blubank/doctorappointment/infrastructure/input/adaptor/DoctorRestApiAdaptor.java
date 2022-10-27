package com.blubank.doctorappointment.infrastructure.input.adaptor;

import com.blubank.doctorappointment.application.ports.input.DoctorServicePort;
import com.blubank.doctorappointment.domain.entity.Doctor;
import com.blubank.doctorappointment.domain.exception.InvalidStartAndEndTimeException;
import com.blubank.doctorappointment.domain.exception.NullMedicalNoException;
import com.blubank.doctorappointment.domain.vo.ID;
import com.blubank.doctorappointment.domain.vo.OpenTime;
import com.blubank.doctorappointment.infrastructure.input.request.CreateDoctorRequest;
import com.blubank.doctorappointment.infrastructure.input.request.CreateOpenTimeRequest;
import com.blubank.doctorappointment.infrastructure.input.request.RemoveOpenTimeRequest;
import com.blubank.doctorappointment.infrastructure.input.response.ResponseFactory;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author a.ariani
 */
@Tag(name = "Doctor")
@RestController
@RequestMapping(path = "/api/v1/doctors")
public class DoctorRestApiAdaptor {
    private final DoctorServicePort doctorServicePort;

    @Autowired
    public DoctorRestApiAdaptor(DoctorServicePort doctorServicePort) {
        this.doctorServicePort = doctorServicePort;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateDoctorRequest createDoctorRequest) {
        return ResponseEntity.ok(doctorServicePort.create(createDoctorRequest.toDomain()));
    }


    @PostMapping(path = "/{id}/open-times")
    public ResponseEntity<?> addOpenTimes(@PathVariable(name = "id") Long id, @Validated @RequestBody CreateOpenTimeRequest createOpenTimeRequest) {
        try {
            Doctor openTime = doctorServicePort.createOpenTime(ID.of(id), OpenTime.of(createOpenTimeRequest.getVisitDate(), createOpenTimeRequest.getStartTime(), createOpenTimeRequest.getEndTime()));
            return ResponseFactory.ok(openTime);
        } catch (InvalidStartAndEndTimeException | NullMedicalNoException e) {
            return ResponseFactory.badRequest(e.getMessage());
        }
    }

    @GetMapping(path = "/appointments")
    public ResponseEntity<?> getAppointments(@RequestParam(name = "id") Long id) {
        return ResponseEntity.ok(doctorServicePort.findAllDoctorOpenAndTakenTimes(ID.of(id)));
    }

    @DeleteMapping(path = "/{id}/open-times")
    public ResponseEntity<?> deleteOpenTimes(@PathVariable(name = "id") Long id,
                                             @RequestBody RemoveOpenTimeRequest openTime) {
        doctorServicePort.removeOpenTime(ID.of(id), openTime.toDomain());
        return ResponseFactory.noContent("deleted");
    }
}
