package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.model.Log;
import com.example.demo.model.LogGanado;
import com.example.demo.model.LogGanadoEntity;
import com.example.demo.service.LogGanadoService;

@RestController
@RequestMapping("/api")
public class LogController {

	private static final Logger logger = LoggerFactory.getLogger(LogController.class);

	@PostMapping("/log")
	public ResponseEntity<Log> crearLog(@RequestBody Log log) {

		logger.info("El endpoint /api/log ha sido invocado");
		// logger.warn("Esto es un mensaje de advertencia");
		logger.info("Se recibieron los siguientes datos:");
		logger.info("	ID: " + log.getId());
		logger.info("	Date: " + log.getDate());
		// logger.error("Esto es un mensaje de error");

		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	/*
	 * @PostMapping("/logGanado") public ResponseEntity<LogGanado>
	 * crearLogGanado(@RequestBody LogGanado log) {
	 * 
	 * // Logica para grabar el log debe ir acá
	 * 
	 * // 2. Validación defensiva antes de extraer coordenadas double latitud = 0.0;
	 * double longitud = 0.0; if (log.getCoordenadas() != null &&
	 * log.getCoordenadas().size() >= 2) { latitud = log.getCoordenadas().get(0);
	 * longitud = log.getCoordenadas().get(1); }
	 * 
	 * logger.info("El endpoint /api/logGanado ha sido invocado");
	 * logger.info("Se recibieron los siguientes datos:"); logger.info("	ID: " +
	 * log.getId()); logger.info("	Temperatura: " + log.getTemperatura() + "°C");
	 * logger.info("	Coordenadas: " + latitud + ", " + longitud);
	 * 
	 * return new ResponseEntity<>(HttpStatus.CREATED); }
	 */
	@Autowired
	private LogGanadoService logGanadoService;

	@PostMapping("/logGanado")
	public ResponseEntity<LogGanadoEntity> crearLogGanado(@Valid @RequestBody LogGanado log) {
		logger.info("El endpoint /api/logGanado ha sido invocado.");

		LogGanadoEntity resultado = logGanadoService.guardarLog(log);

		logger.info("Registro de telemetría guardado exitosamente con ID: {}", resultado.getId());
		return new ResponseEntity<>(resultado, HttpStatus.CREATED);
	}

}