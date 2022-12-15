package com.arash.hexagonal.infrastructure.input.adaptor;

import com.arash.hexagonal.application.ports.input.DoctorServicePort;
import com.arash.hexagonal.domain.vo.OpenTime;
import com.arash.hexagonal.domain.entity.Doctor;
import com.arash.hexagonal.domain.exception.InvalidStartAndEndTimeException;
import com.arash.hexagonal.domain.exception.NullMedicalNoException;
import com.arash.hexagonal.domain.vo.ID;
import com.arash.hexagonal.infrastructure.input.request.CreateDoctorRequest;
import com.arash.hexagonal.infrastructure.input.request.CreateOpenTimeRequest;
import com.arash.hexagonal.infrastructure.input.request.RemoveOpenTimeRequest;
import com.arash.hexagonal.infrastructure.input.response.DoctorAppointmentResponse;
import com.arash.hexagonal.infrastructure.input.response.DoctorResponse;
import com.arash.hexagonal.infrastructure.input.response.ResponseFactory;
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
        return ResponseFactory.ok(doctorServicePort.create(createDoctorRequest.toDomain()), DoctorResponse::from);
    }

    @PostMapping(path = "/{id}/open-times")
    public ResponseEntity<?> addOpenTimes(@PathVariable(name = "id") Long id, @Validated @RequestBody CreateOpenTimeRequest createOpenTimeRequest) {
        try {
            Doctor openTime = doctorServicePort.createOpenTime(ID.of(id), OpenTime.of(createOpenTimeRequest.getVisitDate(), createOpenTimeRequest.getStartTime(), createOpenTimeRequest.getEndTime()));
            return ResponseFactory.ok(openTime, DoctorResponse::from);
        } catch (InvalidStartAndEndTimeException | NullMedicalNoException e) {
            return ResponseFactory.badRequest(e.getMessage());
        }
    }

    @GetMapping(path = "/appointments")
    public ResponseEntity<?> getAppointments(@RequestParam(name = "id") Long id) {
        return ResponseFactory.ok(doctorServicePort.findAllDoctorOpenAndTakenTimes(ID.of(id)), DoctorAppointmentResponse::from);
    }

    @DeleteMapping(path = "/{id}/open-times")
    public ResponseEntity<?> deleteOpenTimes(@PathVariable(name = "id") Long id,
                                             @RequestBody RemoveOpenTimeRequest openTime) {
        doctorServicePort.removeOpenTime(ID.of(id), openTime.toDomain());
        return ResponseFactory.noContent("deleted");
    }
}
