package com.followers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Application {

	@Value("${graphql.authorization}")
	private String auth;

	@Value("${graphql.url}")
	private String url;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
