package com.company.arss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ArssApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArssApplication.class, args);
	}

}

