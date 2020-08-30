package com.cleitons.silvar.consultaCepApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class ConsultaCepApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsultaCepApiApplication.class, args);
	}
}
