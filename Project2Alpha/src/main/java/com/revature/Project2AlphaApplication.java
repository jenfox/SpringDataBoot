package com.revature;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Project2AlphaApplication {
	private static Logger logger = LogManager.getLogger();

	public static void main(String[] args) {
		logger.info("Started App");
		SpringApplication.run(Project2AlphaApplication.class, args);
	}
}
