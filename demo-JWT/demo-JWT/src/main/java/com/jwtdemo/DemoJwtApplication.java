package com.jwtdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@SpringBootApplication
public class DemoJwtApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoJwtApplication.class, args);
	}
	@Bean
	public NoOpPasswordEncoder noOpPasswordEncoder(){
		return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
	}
}
