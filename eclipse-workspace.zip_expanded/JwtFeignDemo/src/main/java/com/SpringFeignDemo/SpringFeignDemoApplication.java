package com.SpringFeignDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SpringFeignDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringFeignDemoApplication.class, args);
	}

}
