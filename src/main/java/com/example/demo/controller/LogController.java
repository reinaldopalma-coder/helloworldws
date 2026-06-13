package com.example.demo.controller;

import com.example.demo.model.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/log")
public class LogController {
	// Binds HTTP POST requests to http://localhost:8080/api/log
	@PostMapping
	public ResponseEntity<Log> createLog(@RequestBody Log log) {
	    // Logic to save the log would go here (e.g., logRepository.save(log))
	   
	    // Return the created user with a 201 Created status
		return new ResponseEntity<>(log, HttpStatus.CREATED);
	}
}
