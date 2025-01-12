package com.blahblah.accounts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
			info = @Info(
						title = "Accounts Microservice",
						contact = @Contact(
									name = "Blah Blah", 
									email = "blahblah@mailinator.com",
									url = "www.google.com"
								),
						description = "Accounts Microservice Description", 
						license = @License(
									name = "Blah License",
									url = "www.google.com"
								),
						version = "v1"
					),
			externalDocs = @ExternalDocumentation(
						description = "To know more please google(You Won't Find Anything!!!)",
						url = "www.google.com"
					)
		)
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}
