package com.tech_challenge_fiap;

import io.mongock.runner.springboot.EnableMongock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableMongock
public class TechChallengeFiapApplication {

	public static void main(String[] args) {
		SpringApplication.run(TechChallengeFiapApplication.class, args);
	}

}
