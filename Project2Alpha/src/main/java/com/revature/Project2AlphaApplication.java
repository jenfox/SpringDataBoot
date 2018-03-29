package com.revature;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Project2AlphaApplication {
	private static final Logger logger = LoggerFactory.getLogger(Project2AlphaApplication.class);

	public static void main(String[] args) {
		logger.info("Started App");
		SpringApplication.run(Project2AlphaApplication.class, args);
	}
}
