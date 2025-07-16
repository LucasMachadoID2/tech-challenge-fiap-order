package com.tech_challenge_fiap;

import com.tech_challenge_fiap.scripts.PaymentStatusMigration;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import static org.mockito.Mockito.mock;

@SpringBootTest
class TechChallengeFiapApplicationTests {

	@TestConfiguration
	static class TestConfig {
		@Bean
		public PaymentStatusMigration paymentStatusMigration() {
			return mock(PaymentStatusMigration.class);
		}
	}

	@Test
	void contextLoads() {
	}

}
