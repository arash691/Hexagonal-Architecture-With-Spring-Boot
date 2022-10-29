package com.blubank.doctorappointment.infrastructure.config;

import com.blubank.doctorappointment.application.ports.input.DoctorServicePort;
import com.blubank.doctorappointment.application.ports.input.DoctorServicePortAdaptor;
import com.blubank.doctorappointment.application.ports.input.PatientServicePort;
import com.blubank.doctorappointment.application.ports.input.PatientServicePortAdaptor;
import com.blubank.doctorappointment.application.ports.output.DoctorPersistencePort;
import com.blubank.doctorappointment.application.ports.output.PatientPersistencePort;
import com.blubank.doctorappointment.infrastructure.output.AppointmentRepository;
import com.blubank.doctorappointment.infrastructure.output.DoctorRepository;
import com.blubank.doctorappointment.infrastructure.output.PatientRepository;
import com.blubank.doctorappointment.infrastructure.output.adaptor.DoctorPersistenceAdaptor;
import com.blubank.doctorappointment.infrastructure.output.adaptor.PatientPersistenceAdaptor;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.media.StringSchema;
import org.springdoc.core.SpringDocUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalTime;

/**
 * @author a.ariani
 */
@OpenAPIDefinition(info = @Info(title = "Doctor-Appointment", version = "0.0.1-SNAPSHOT"))
@Configuration
public class ApplicationConfig {

    static {
        SpringDocUtils.getConfig().replaceWithSchema(LocalTime.class, new StringSchema().format("HH:mm"));
    }

    @Bean
    public DoctorServicePort doctorService(DoctorPersistencePort doctorPersistencePort) {
        return new DoctorServicePortAdaptor(doctorPersistencePort);
    }

    @Bean
    public DoctorPersistencePort doctorPersistencePort(DoctorRepository doctorRepository, AppointmentRepository appointmentRepository) {
        return new DoctorPersistenceAdaptor(doctorRepository, appointmentRepository);
    }

    @Bean
    public PatientPersistencePort patientPersistencePort(PatientRepository patientRepository, AppointmentRepository appointmentRepository) {
        return new PatientPersistenceAdaptor(patientRepository, appointmentRepository);
    }

    @Bean
    public PatientServicePort patientServicePort(PatientPersistencePort patientPersistencePort) {
        return new PatientServicePortAdaptor(patientPersistencePort);
    }

}
