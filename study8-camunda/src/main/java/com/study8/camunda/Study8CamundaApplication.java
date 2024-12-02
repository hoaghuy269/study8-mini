package com.study8.camunda;

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableProcessApplication
public class Study8CamundaApplication {

	public static void main(String[] args) {
		SpringApplication.run(Study8CamundaApplication.class, args);
	}

}