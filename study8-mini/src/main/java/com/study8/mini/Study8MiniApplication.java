package com.study8.mini;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Study8MiniApplication {

	public static void main(String[] args) {
		SpringApplication.run(Study8MiniApplication.class, args);
	}

}
