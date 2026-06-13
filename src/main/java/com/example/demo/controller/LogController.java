package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Log;

@RestController
@RequestMapping("/api")
public class LogController {

	private static final Logger logger = LoggerFactory.getLogger(LogController.class);

	// Link individual variables from the parameters of the request or form
	// In this case we link id and date
	@PostMapping("/log")
	public ResponseEntity<Log> createLog(@RequestBody Log log) {

		// Logic to save the log would go here (e.g., logRepository.save(log))

		logger.info("El endpoint /api/log ha sido invocado");
		//logger.warn("Esto es un mensaje de advertencia");
		logger.info("Se recibieron los siguientes datos:");
		logger.info("	ID: " + log.getId());
		logger.info("	Date: " + log.getDate());
		//logger.error("Esto es un mensaje de error");

		// Return the created user with a 201 Created status
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

}