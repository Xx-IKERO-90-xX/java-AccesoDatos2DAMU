package com.vtinstitute.vtinstitute_restapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;

import jakarta.persistence.Entity;

@SpringBootApplication
@EntityScan(basePackages = "com.vtinstitute.vtinstitute_restapi.model.entity")
public class VtinstituteRestapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(VtinstituteRestapiApplication.class, args);
	}

}
