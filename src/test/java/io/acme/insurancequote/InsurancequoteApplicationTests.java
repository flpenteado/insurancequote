package io.acme.insurancequote;

import org.springframework.amqp.rabbit.test.context.SpringRabbitTest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.testcontainers.containers.PostgreSQLContainer;

@SpringBootTest(classes = InsurancequoteApplicationTests.BaseConfiguration.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@SpringJUnitConfig
@SpringRabbitTest

@AutoConfigureMockMvc

public class InsurancequoteApplicationTests {

	private static final PostgreSQLContainer<?> POSTGRESQL_CONTAINER = io.acme.insurancequote.infrastructure.config.PostgresContainer
			.getInstance()
			.withDatabaseName("insurancequote")
			.withUsername("db_user")
			.withPassword("secret");

	static {
		POSTGRESQL_CONTAINER.start();
	}

	@SpringBootApplication
	static class BaseConfiguration {
		public static void main(String[] args) {
			SpringApplication.run(BaseConfiguration.class, args);
		}

		@Bean
		public ServletWebServerFactory servletWebServerFactory() {
			return new TomcatServletWebServerFactory();
		}
	}
}
