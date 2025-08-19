package com.cabanasmakai.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CabanasMakaiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CabanasMakaiApplication.class, args);
	}

}
