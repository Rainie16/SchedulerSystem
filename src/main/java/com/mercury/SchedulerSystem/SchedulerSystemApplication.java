package com.mercury.SchedulerSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SchedulerSystemApplication {

	private int maxUploadSizeInMb = 10 * 1024 * 1024; //10 MB

	public static void main(String[] args) {
		SpringApplication.run(SchedulerSystemApplication.class, args);
	}

}
