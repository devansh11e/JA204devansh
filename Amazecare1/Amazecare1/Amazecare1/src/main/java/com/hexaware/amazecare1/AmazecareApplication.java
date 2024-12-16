package com.hexaware.amazecare1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.hexaware.amazecare1")
public class AmazecareApplication {

	public static void main(String[] args) {
		SpringApplication.run(AmazecareApplication.class, args);
	}

}
