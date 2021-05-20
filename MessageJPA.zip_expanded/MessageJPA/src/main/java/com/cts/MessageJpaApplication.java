package com.cts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication

public class MessageJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MessageJpaApplication.class, args);
	}

}
