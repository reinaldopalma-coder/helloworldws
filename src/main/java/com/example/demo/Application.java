package com.example.demo;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@PostConstruct
    public void init() {
        // Forza a toda la aplicación de Java a usar la hora de tu país
        TimeZone.setDefault(TimeZone.getTimeZone("America/Santiago"));
    }

}
