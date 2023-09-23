package com.lucaslucenak.Demacia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DemaciaApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemaciaApplication.class, args);
	}

}
