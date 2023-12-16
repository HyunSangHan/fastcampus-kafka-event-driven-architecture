package com.fastcampus.kafkahandson.ugc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ContentSubscribingWorkerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContentSubscribingWorkerApplication.class, args);
	}

}
