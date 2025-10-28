package br.com.clinicamedica.service_registry_clinicamedica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ServiceRegistryClinicamedicaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceRegistryClinicamedicaApplication.class, args);
	}

}
