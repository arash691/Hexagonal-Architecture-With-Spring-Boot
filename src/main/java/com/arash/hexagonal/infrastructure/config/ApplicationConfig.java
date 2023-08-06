package com.arash.hexagonal.infrastructure.config;

import com.arash.hexagonal.application.ports.input.DoctorServicePort;
import com.arash.hexagonal.application.ports.input.DoctorServicePortAdaptor;
import com.arash.hexagonal.application.ports.input.PatientServicePort;
import com.arash.hexagonal.application.ports.input.PatientServicePortAdaptor;
import com.arash.hexagonal.application.ports.output.DoctorPersistencePort;
import com.arash.hexagonal.application.ports.output.PatientPersistencePort;
import com.arash.hexagonal.infrastructure.output.AppointmentRepository;
import com.arash.hexagonal.infrastructure.output.DoctorRepository;
import com.arash.hexagonal.infrastructure.output.PatientRepository;
import com.arash.hexagonal.infrastructure.output.adaptor.DoctorPersistenceAdaptor;
import com.arash.hexagonal.infrastructure.output.adaptor.PatientPersistenceAdaptor;
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
