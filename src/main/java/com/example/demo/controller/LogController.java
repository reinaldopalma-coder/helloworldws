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
import com.example.demo.model.LogGanado;

@RestController
@RequestMapping("/api")
public class LogController {

	private static final Logger logger = LoggerFactory.getLogger(LogController.class);

	@PostMapping("/log")
	public ResponseEntity<Log> createLog(@RequestBody Log log) {

		logger.info("El endpoint /api/log ha sido invocado");
		//logger.warn("Esto es un mensaje de advertencia");
		logger.info("Se recibieron los siguientes datos:");
		logger.info("	ID: " + log.getId());
		logger.info("	Date: " + log.getDate());
		//logger.error("Esto es un mensaje de error");

		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PostMapping("/logGanado")
	public ResponseEntity<LogGanado> crearLogGanado(@RequestBody LogGanado log) {

		// Logica para grabar el log debe ir acá
		
		double latitud = log.getCoordenadas().get(0);
        double longitud = log.getCoordenadas().get(1);
		
		logger.info("El endpoint /api/logGanado ha sido invocado");
		logger.info("Se recibieron los siguientes datos:");
		logger.info("	ID: " + log.getId());
		logger.info("	Temperatura: " + log.getTemperatura() + "°C");
		logger.info("	Coordenadas: " + latitud + ", " + longitud);
		
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

}