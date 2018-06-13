package com.example.democlient;

import com.example.democlient.domain.ContactPerson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class DemoclientApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(DemoclientApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DemoclientApplication.class, args);
	}

	@Bean
	CommandLineRunner init() {
		return args -> {
			WebClient webClient = WebClient.builder()
					.baseUrl("http://localhost:8081")
					.build();

			webClient
					.get()
					.uri("/api/v2/contact")
					.accept(MediaType.APPLICATION_JSON_UTF8)
					.retrieve()
					.bodyToFlux(ContactPerson.class)
					.subscribe(DemoclientApplication::reverseName);

		};
	}

	private static void reverseName(ContactPerson contactPerson) {
		String reversedName = new StringBuilder(contactPerson.getName()).reverse().toString();
		LOGGER.info("I just reversed {} : {}", contactPerson.getName(), reversedName);
	}

}
