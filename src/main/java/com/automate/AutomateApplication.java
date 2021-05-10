package com.automate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:errormsgs.properties")
public class AutomateApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutomateApplication.class, args);
	}
}
