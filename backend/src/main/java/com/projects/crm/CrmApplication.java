package com.projects.crm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAutoConfiguration
@SpringBootApplication
public class CrmApplication {
	public static void main(String[] args) {
		SpringApplication.run(CrmApplication.class, args);
	}
}
