package com.rtitipco.kafkatofolder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkatofolderApplication {

	public static void main(String[] args) {

		Logger log = LoggerFactory.getLogger(KafkatofolderApplication.class);

		SpringApplication.run(KafkatofolderApplication.class, args);

		log.info("Process started........");
	}

}
