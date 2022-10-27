package com.blubank.doctorappointment;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableAdminServer
@EnableJpaAuditing
@EntityScan(basePackages = {"com.blubank.doctorappointment.infrastructure"})
@EnableConfigurationProperties
@EnableJpaRepositories("com.blubank.doctorappointment.infrastructure")
@SpringBootApplication(scanBasePackages= "com.blubank.doctorappointment.infrastructure")
public class DoctorAppointmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(DoctorAppointmentApplication.class, args);
	}

}
