package com.study8.mini;

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableProcessApplication
@EnableScheduling
public class Study8MiniApplication {

	public static void main(String[] args) {
		SpringApplication.run(Study8MiniApplication.class, args);
	}

}
