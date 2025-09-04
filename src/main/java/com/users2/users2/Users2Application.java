package com.users2.users2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class Users2Application {

	public static void main(String[] args) {
		SpringApplication.run(Users2Application.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("https://albo-barber-front.vercel.app/", "http://localhost:3000", "https://albobarberia.com/", "https://www.albobarberia.com/")
						.allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
						.allowedHeaders("Content-Type", "Authorization", "X-Requested-With", "Accept")
						.allowCredentials(true);
			}
		};
	}
}
