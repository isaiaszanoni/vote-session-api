package com.siccase.vote_session_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class VoteSessionApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(VoteSessionApiApplication.class, args);
	}

}
